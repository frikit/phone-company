# Phone Company

Each day at The Phone Company a batch job puts all the customer calls for the previous day into a single log file of:

`'customer id','phone number called','call duration'`

For a customer the cost of a call up to and including 3 minutes in duration is charged at 0.05p/sec, any call over 3 minutes in duration the additional time is charged at 0.03p/sec. However, there is a promotion on and the calls made to the phone number with the greatest total cost is removed from the customer's bill.

## Task

Write a program that when run will parse the `calls.log` file and print out the total cost of calls for the day for each customer. You can use any libraries you wish to.

## Log example

```log
A 555-333-212 00:02:03
A 555-433-242 00:06:41
A 555-433-242 00:01:03
B 555-333-212 00:01:20
A 555-333-212 00:01:10
A 555-663-111 00:02:09
A 555-333-212 00:04:28
B 555-334-789 00:00:03
A 555-663-111 00:02:03
B 555-334-789 00:00:53
B 555-971-219 00:09:51
B 555-333-212 00:02:03
B 555-333-212 00:04:31
B 555-334-789 00:01:59


```
