package cryptography;

import errors.HashError;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    private String stringToHash;
    private String hash;
    private String beginningOfHash;

    /**
     * Constructor for Hash
     *
     * @param stringToHash the string that is to be hashed
     */
    public Hash(String stringToHash) {
        this.stringToHash = stringToHash;
    }

    /**
     * Hashes a string, in this case with SHA-1
     *
     * @throws NoSuchAlgorithmException if SHA-1 ceases to exist
     */
    private void hashAndHex() throws NoSuchAlgorithmException {

        StringBuilder hashHex = new StringBuilder();

        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] bytes = digest.digest(stringToHash.getBytes(StandardCharsets.UTF_8));

        for (byte x : bytes) hashHex.append(Integer.toHexString(0xFF & x));

        setHash(hashHex.toString());

    }

    /**
     * Get the first 5 characters of the hash, for k-anonymity when sending the hash. I.e. you only send part of the
     * hash, so a reverse lookup or other vulnerability isn't possible
     */
    private void setBeginningOfHash() {
        this.beginningOfHash = hash.substring(0, 5);
    }

    /**
     * Get the full hash
     *
     * @return the hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * Set the hash for use later
     *
     * @param hash the hash to set
     */
    private void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * Get the first 5 characters of the hash
     *
     * @return only the first 5 characters of the hash
     */
    public String getBeginningOfHash() {
        return beginningOfHash;
    }

    /**
     * Run everything
     *
     * @throws HashError if an algorithm doesn't exist (wrapper for NoSuchAlgorithmException).
     */
    public void runHash() throws HashError {
        try {
            hashAndHex();
            setBeginningOfHash();
        } catch (NoSuchAlgorithmException e) {
            throw new HashError("HashError: " + e.getMessage());
        }
    }

}
