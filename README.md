# typing-patterns

This is a POC for using user's typing pattern for authentication.
While signing up the user is prompted to enter his password 10 times.

We calculate average presstime, interkeytime and total time to enter 
the password and store it in database.

When the user logs in, he enters his password. 
We again calculate the same metrics and also the deviation of the
values from teh ones in the DB.
If the deviation is less than some threshold (say 10%), we allow the user to login.

For more, refer to [User Authentication Through Typing Biometrics : An Implementation](https://www.academia.edu/8607235/User_Authentication_Through_Typing_Biometrics_An_Implementation)
