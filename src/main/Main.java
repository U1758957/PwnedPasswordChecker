package main;

import errors.MainError;

public class Main {

    public static void main(String[] args) throws MainError {

        String password;

        if (args.length == 1) password = args[0];
        else throw new MainError(args.length < 1 ?
                "MainError: No Arguments Specified" :
                "MainError: Too Many Arguments");

    }

}
