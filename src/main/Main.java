package main;

import cryptography.Hash;
import errors.HashError;
import errors.MainError;
import errors.NetworkError;
import network.HTTPSRequest;

import java.util.Map;

public class Main {

    public static void main(String[] args) throws MainError, HashError, NetworkError {

        String password;

        if (args.length == 1) password = args[0];
        else throw new MainError(args.length < 1 ?
                "MainError: No Arguments Specified" :
                "MainError: Too Many Arguments");

        Hash hash = new Hash(password);
        hash.runHash();

        HTTPSRequest httpsRequest = new HTTPSRequest(hash.getBeginningOfHash());
        Map<String, Integer> hashDictionary = httpsRequest.hashDict();

        if (hashDictionary.containsKey(hash.getHash())) {
            Integer hashOccurrence = hashDictionary.get(hash.getHash());
            String plural = hashOccurrence == 1 ? " time!" : " times!";
            System.out.println("Warning! Password found " + hashOccurrence + plural);
        } else {
            System.out.println("Congratulations! Your password has not been found");
        }

    }
}
