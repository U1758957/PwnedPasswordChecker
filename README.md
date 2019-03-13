# PwnedPasswordChecker
Checks if your password has been leaked before using this: https://haveibeenpwned.com/Passwords

Usage #1:

In a command line / powershell, type (without quotations or square brackets): "java -jar PwnedPasswordChecker.jar [password]"
Beware that this method could save your history in a terminal / command line, and be viewable by another user of your computer.

e.g.

`java -jar PwnedPasswordChecker.jar password12345`

Usage #2:

In a command line / powershell, just type (without quotations or square brackets): "java -jar PwnedPasswordChecker.jar"
This is the more secure method, as it accepts a user input from within the program instead of a command line, and as such no history is saved anywhere/

e.g.

`java -jar PwnedPasswordChecker.jar`
