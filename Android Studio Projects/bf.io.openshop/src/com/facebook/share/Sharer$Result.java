package com.facebook.share;

public static class Result
{
    final String postId;
    
    public Result(final String postId) {
        this.postId = postId;
    }
    
    public String getPostId() {
        return this.postId;
    }
}
