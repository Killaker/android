package okhttp3.internal.http;

import okhttp3.internal.*;
import java.io.*;
import java.net.*;
import okhttp3.*;
import java.util.*;

public final class RouteSelector
{
    private final Address address;
    private List<InetSocketAddress> inetSocketAddresses;
    private InetSocketAddress lastInetSocketAddress;
    private Proxy lastProxy;
    private int nextInetSocketAddressIndex;
    private int nextProxyIndex;
    private final List<Route> postponedRoutes;
    private List<Proxy> proxies;
    private final RouteDatabase routeDatabase;
    
    public RouteSelector(final Address address, final RouteDatabase routeDatabase) {
        this.proxies = Collections.emptyList();
        this.inetSocketAddresses = Collections.emptyList();
        this.postponedRoutes = new ArrayList<Route>();
        this.address = address;
        this.routeDatabase = routeDatabase;
        this.resetNextProxy(address.url(), address.proxy());
    }
    
    static String getHostString(final InetSocketAddress inetSocketAddress) {
        final InetAddress address = inetSocketAddress.getAddress();
        if (address == null) {
            return inetSocketAddress.getHostName();
        }
        return address.getHostAddress();
    }
    
    private boolean hasNextInetSocketAddress() {
        return this.nextInetSocketAddressIndex < this.inetSocketAddresses.size();
    }
    
    private boolean hasNextPostponed() {
        return !this.postponedRoutes.isEmpty();
    }
    
    private boolean hasNextProxy() {
        return this.nextProxyIndex < this.proxies.size();
    }
    
    private InetSocketAddress nextInetSocketAddress() throws IOException {
        if (!this.hasNextInetSocketAddress()) {
            throw new SocketException("No route to " + this.address.url().host() + "; exhausted inet socket addresses: " + this.inetSocketAddresses);
        }
        return this.inetSocketAddresses.get(this.nextInetSocketAddressIndex++);
    }
    
    private Route nextPostponed() {
        return this.postponedRoutes.remove(0);
    }
    
    private Proxy nextProxy() throws IOException {
        if (!this.hasNextProxy()) {
            throw new SocketException("No route to " + this.address.url().host() + "; exhausted proxy configurations: " + this.proxies);
        }
        final Proxy proxy = this.proxies.get(this.nextProxyIndex++);
        this.resetNextInetSocketAddress(proxy);
        return proxy;
    }
    
    private void resetNextInetSocketAddress(final Proxy proxy) throws IOException {
        this.inetSocketAddresses = new ArrayList<InetSocketAddress>();
        String s;
        int n;
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
            s = this.address.url().host();
            n = this.address.url().port();
        }
        else {
            final SocketAddress address = proxy.address();
            if (!(address instanceof InetSocketAddress)) {
                throw new IllegalArgumentException("Proxy.address() is not an InetSocketAddress: " + ((InetSocketAddress)address).getClass());
            }
            final InetSocketAddress inetSocketAddress = (InetSocketAddress)address;
            s = getHostString(inetSocketAddress);
            n = inetSocketAddress.getPort();
        }
        if (n < 1 || n > 65535) {
            throw new SocketException("No route to " + s + ":" + n + "; port is out of range");
        }
        if (proxy.type() == Proxy.Type.SOCKS) {
            this.inetSocketAddresses.add(InetSocketAddress.createUnresolved(s, n));
        }
        else {
            final List<InetAddress> lookup = this.address.dns().lookup(s);
            for (int i = 0; i < lookup.size(); ++i) {
                this.inetSocketAddresses.add(new InetSocketAddress(lookup.get(i), n));
            }
        }
        this.nextInetSocketAddressIndex = 0;
    }
    
    private void resetNextProxy(final HttpUrl httpUrl, final Proxy proxy) {
        if (proxy != null) {
            this.proxies = Collections.singletonList(proxy);
        }
        else {
            this.proxies = new ArrayList<Proxy>();
            final List<Proxy> select = this.address.proxySelector().select(httpUrl.uri());
            if (select != null) {
                this.proxies.addAll(select);
            }
            this.proxies.removeAll(Collections.singleton(Proxy.NO_PROXY));
            this.proxies.add(Proxy.NO_PROXY);
        }
        this.nextProxyIndex = 0;
    }
    
    public void connectFailed(final Route route, final IOException ex) {
        if (route.proxy().type() != Proxy.Type.DIRECT && this.address.proxySelector() != null) {
            this.address.proxySelector().connectFailed(this.address.url().uri(), route.proxy().address(), ex);
        }
        this.routeDatabase.failed(route);
    }
    
    public boolean hasNext() {
        return this.hasNextInetSocketAddress() || this.hasNextProxy() || this.hasNextPostponed();
    }
    
    public Route next() throws IOException {
        Label_0044: {
            if (this.hasNextInetSocketAddress()) {
                break Label_0044;
            }
            if (this.hasNextProxy()) {
                this.lastProxy = this.nextProxy();
                break Label_0044;
            }
            if (!this.hasNextPostponed()) {
                throw new NoSuchElementException();
            }
            return this.nextPostponed();
        }
        this.lastInetSocketAddress = this.nextInetSocketAddress();
        final Route nextPostponed = new Route(this.address, this.lastProxy, this.lastInetSocketAddress);
        if (this.routeDatabase.shouldPostpone(nextPostponed)) {
            this.postponedRoutes.add(nextPostponed);
            return this.next();
        }
        return nextPostponed;
    }
}
