package com.rat.squad.storage.provider;

public interface TokenProvider {
    boolean validateToken(String token);
}
