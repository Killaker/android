package android.support.multidex;

import android.content.*;
import android.util.*;
import android.content.pm.*;
import android.os.*;
import java.lang.reflect.*;
import java.io.*;
import java.util.regex.*;
import java.util.zip.*;
import dalvik.system.*;
import java.util.*;

public final class MultiDex
{
    private static final boolean IS_VM_MULTIDEX_CAPABLE = false;
    private static final int MAX_SUPPORTED_SDK_VERSION = 20;
    private static final int MIN_SDK_VERSION = 4;
    private static final String OLD_SECONDARY_FOLDER_NAME = "secondary-dexes";
    private static final String SECONDARY_FOLDER_NAME;
    static final String TAG = "MultiDex";
    private static final int VM_WITH_MULTIDEX_VERSION_MAJOR = 2;
    private static final int VM_WITH_MULTIDEX_VERSION_MINOR = 1;
    private static final Set<String> installedApk;
    
    static {
        SECONDARY_FOLDER_NAME = "code_cache" + File.separator + "secondary-dexes";
        installedApk = new HashSet<String>();
    }
    
    private static boolean checkValidZipFiles(final List<File> list) {
        final Iterator<File> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (!MultiDexExtractor.verifyZipFile(iterator.next())) {
                return false;
            }
        }
        return true;
    }
    
    private static void clearOldDexDir(final Context context) throws Exception {
        final File file = new File(context.getFilesDir(), "secondary-dexes");
        if (file.isDirectory()) {
            Log.i("MultiDex", "Clearing old secondary dex dir (" + file.getPath() + ").");
            final File[] listFiles = file.listFiles();
            if (listFiles == null) {
                Log.w("MultiDex", "Failed to list secondary dex dir content (" + file.getPath() + ").");
            }
            else {
                for (final File file2 : listFiles) {
                    Log.i("MultiDex", "Trying to delete old file " + file2.getPath() + " of size " + file2.length());
                    if (!file2.delete()) {
                        Log.w("MultiDex", "Failed to delete old file " + file2.getPath());
                    }
                    else {
                        Log.i("MultiDex", "Deleted old file " + file2.getPath());
                    }
                }
                if (!file.delete()) {
                    Log.w("MultiDex", "Failed to delete secondary dex dir " + file.getPath());
                    return;
                }
                Log.i("MultiDex", "Deleted old secondary dex dir " + file.getPath());
            }
        }
    }
    
    private static void expandFieldArray(final Object o, final String s, final Object[] array) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        final Field field = findField(o, s);
        final Object[] array2 = (Object[])field.get(o);
        final Object[] array3 = (Object[])Array.newInstance(array2.getClass().getComponentType(), array2.length + array.length);
        System.arraycopy(array2, 0, array3, 0, array2.length);
        System.arraycopy(array, 0, array3, array2.length, array.length);
        field.set(o, array3);
    }
    
    private static Field findField(final Object o, final String s) throws NoSuchFieldException {
        Class<?> clazz = o.getClass();
        while (clazz != null) {
            try {
                final Field declaredField = clazz.getDeclaredField(s);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                return declaredField;
            }
            catch (NoSuchFieldException ex) {
                clazz = clazz.getSuperclass();
                continue;
            }
            break;
        }
        throw new NoSuchFieldException("Field " + s + " not found in " + o.getClass());
    }
    
    private static Method findMethod(final Object o, final String s, final Class<?>... array) throws NoSuchMethodException {
        Class<?> clazz = o.getClass();
        while (clazz != null) {
            try {
                final Method declaredMethod = clazz.getDeclaredMethod(s, array);
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            }
            catch (NoSuchMethodException ex) {
                clazz = clazz.getSuperclass();
                continue;
            }
            break;
        }
        throw new NoSuchMethodException("Method " + s + " with parameters " + Arrays.asList(array) + " not found in " + o.getClass());
    }
    
    private static ApplicationInfo getApplicationInfo(final Context context) throws PackageManager$NameNotFoundException {
        PackageManager packageManager;
        String packageName;
        try {
            packageManager = context.getPackageManager();
            packageName = context.getPackageName();
            if (packageManager == null || packageName == null) {
                return null;
            }
        }
        catch (RuntimeException ex) {
            Log.w("MultiDex", "Failure while trying to obtain ApplicationInfo from Context. Must be running in test mode. Skip patching.", (Throwable)ex);
            return null;
        }
        return packageManager.getApplicationInfo(packageName, 128);
    }
    
    public static void install(final Context context) {
        Log.i("MultiDex", "install");
        if (MultiDex.IS_VM_MULTIDEX_CAPABLE) {
            Log.i("MultiDex", "VM has multidex support, MultiDex support library is disabled.");
        }
        else {
            if (Build$VERSION.SDK_INT < 4) {
                throw new RuntimeException("Multi dex installation failed. SDK " + Build$VERSION.SDK_INT + " is unsupported. Min SDK version is " + 4 + ".");
            }
            ApplicationInfo applicationInfo;
            String sourceDir;
            try {
                applicationInfo = getApplicationInfo(context);
                if (applicationInfo == null) {
                    return;
                }
                synchronized (MultiDex.installedApk) {
                    sourceDir = applicationInfo.sourceDir;
                    if (MultiDex.installedApk.contains(sourceDir)) {
                        return;
                    }
                }
            }
            catch (Exception ex) {
                Log.e("MultiDex", "Multidex installation failure", (Throwable)ex);
                throw new RuntimeException("Multi dex installation failed (" + ex.getMessage() + ").");
            }
            MultiDex.installedApk.add(sourceDir);
            if (Build$VERSION.SDK_INT > 20) {
                Log.w("MultiDex", "MultiDex is not guaranteed to work in SDK version " + Build$VERSION.SDK_INT + ": SDK version higher than " + 20 + " should be backed by " + "runtime with built-in multidex capabilty but it's not the " + "case here: java.vm.version=\"" + System.getProperty("java.vm.version") + "\"");
            }
            ClassLoader classLoader;
            try {
                classLoader = context.getClassLoader();
                if (classLoader == null) {
                    Log.e("MultiDex", "Context class loader is null. Must be running in test mode. Skip patching.");
                    // monitorexit(set)
                    return;
                }
            }
            catch (RuntimeException ex2) {
                Log.w("MultiDex", "Failure while trying to obtain Context class loader. Must be running in test mode. Skip patching.", (Throwable)ex2);
                // monitorexit(set)
                return;
            }
        Label_0356_Outer:
            while (true) {
                while (true) {
                    File file;
                    while (true) {
                        try {
                            clearOldDexDir(context);
                            file = new File(applicationInfo.dataDir, MultiDex.SECONDARY_FOLDER_NAME);
                            final List<File> load = MultiDexExtractor.load(context, applicationInfo, file, false);
                            if (checkValidZipFiles(load)) {
                                installSecondaryDexes(classLoader, file, load);
                                // monitorexit(set)
                                Log.i("MultiDex", "install done");
                                return;
                            }
                        }
                        catch (Throwable t) {
                            Log.w("MultiDex", "Something went wrong when trying to clear old MultiDex extraction, continuing without cleaning.", t);
                            continue Label_0356_Outer;
                        }
                        break;
                    }
                    Log.w("MultiDex", "Files were not valid zip files.  Forcing a reload.");
                    final List<File> load2 = MultiDexExtractor.load(context, applicationInfo, file, true);
                    if (checkValidZipFiles(load2)) {
                        installSecondaryDexes(classLoader, file, load2);
                        continue;
                    }
                    break;
                }
                break;
            }
            throw new RuntimeException("Zip files were not valid.");
        }
    }
    
    private static void installSecondaryDexes(final ClassLoader classLoader, final File file, final List<File> list) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException {
        if (!list.isEmpty()) {
            if (Build$VERSION.SDK_INT >= 19) {
                install(classLoader, list, file);
            }
            else {
                if (Build$VERSION.SDK_INT >= 14) {
                    install(classLoader, list, file);
                    return;
                }
                install(classLoader, list);
            }
        }
    }
    
    static boolean isVMMultidexCapable(final String s) {
        boolean b = false;
        while (true) {
            if (s == null) {
                break Label_0074;
            }
            final Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(s);
            final boolean matches = matcher.matches();
            b = false;
            if (!matches) {
                break Label_0074;
            }
            try {
                final int int1 = Integer.parseInt(matcher.group(1));
                final int int2 = Integer.parseInt(matcher.group(2));
                b = (int1 > 2 || (int1 == 2 && int2 >= 1));
                final StringBuilder append = new StringBuilder().append("VM with version ").append(s);
                String s2;
                if (b) {
                    s2 = " has multidex support";
                }
                else {
                    s2 = " does not have multidex support";
                }
                Log.i("MultiDex", append.append(s2).toString());
                return b;
            }
            catch (NumberFormatException ex) {
                b = false;
                continue;
            }
            break;
        }
    }
    
    private static final class V14
    {
        private static void install(final ClassLoader classLoader, final List<File> list, final File file) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
            final Object value = findField(classLoader, "pathList").get(classLoader);
            expandFieldArray(value, "dexElements", makeDexElements(value, new ArrayList<File>(list), file));
        }
        
        private static Object[] makeDexElements(final Object o, final ArrayList<File> list, final File file) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            return (Object[])findMethod(o, "makeDexElements", (Class<?>[])new Class[] { ArrayList.class, File.class }).invoke(o, list, file);
        }
    }
    
    private static final class V19
    {
        private static void install(final ClassLoader classLoader, final List<File> list, final File file) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException {
            final Object value = findField(classLoader, "pathList").get(classLoader);
            final ArrayList<IOException> list2 = new ArrayList<IOException>();
            expandFieldArray(value, "dexElements", makeDexElements(value, new ArrayList<File>(list), file, list2));
            if (list2.size() > 0) {
                final Iterator<IOException> iterator = list2.iterator();
                while (iterator.hasNext()) {
                    Log.w("MultiDex", "Exception in makeDexElement", (Throwable)iterator.next());
                }
                final Field access$300 = findField(classLoader, "dexElementsSuppressedExceptions");
                final IOException[] array = (IOException[])access$300.get(classLoader);
                IOException[] array2;
                if (array == null) {
                    array2 = list2.toArray(new IOException[list2.size()]);
                }
                else {
                    final IOException[] array3 = new IOException[list2.size() + array.length];
                    list2.toArray(array3);
                    System.arraycopy(array, 0, array3, list2.size(), array.length);
                    array2 = array3;
                }
                access$300.set(classLoader, array2);
            }
        }
        
        private static Object[] makeDexElements(final Object o, final ArrayList<File> list, final File file, final ArrayList<IOException> list2) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
            return (Object[])findMethod(o, "makeDexElements", (Class<?>[])new Class[] { ArrayList.class, File.class, ArrayList.class }).invoke(o, list, file, list2);
        }
    }
    
    private static final class V4
    {
        private static void install(final ClassLoader classLoader, final List<File> list) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, IOException {
            final int size = list.size();
            final Field access$300 = findField(classLoader, "path");
            final StringBuilder sb = new StringBuilder((String)access$300.get(classLoader));
            final String[] array = new String[size];
            final File[] array2 = new File[size];
            final ZipFile[] array3 = new ZipFile[size];
            final DexFile[] array4 = new DexFile[size];
            final ListIterator<File> listIterator = list.listIterator();
            while (listIterator.hasNext()) {
                final File file = listIterator.next();
                final String absolutePath = file.getAbsolutePath();
                sb.append(':').append(absolutePath);
                final int previousIndex = listIterator.previousIndex();
                array[previousIndex] = absolutePath;
                array2[previousIndex] = file;
                array3[previousIndex] = new ZipFile(file);
                array4[previousIndex] = DexFile.loadDex(absolutePath, absolutePath + ".dex", 0);
            }
            access$300.set(classLoader, sb.toString());
            expandFieldArray(classLoader, "mPaths", array);
            expandFieldArray(classLoader, "mFiles", array2);
            expandFieldArray(classLoader, "mZips", array3);
            expandFieldArray(classLoader, "mDexs", array4);
        }
    }
}
