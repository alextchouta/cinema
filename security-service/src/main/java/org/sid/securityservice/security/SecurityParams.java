package org.sid.securityservice.security;

public interface SecurityParams {
    public static final String HEADER_NAME ="Authorization";
    public static final String SECRET ="med@youssfi.net";
    public static final long EXPIRATION =10*24*3600;
    public static final String HEADER_PREFIX="Bearer ";
}
