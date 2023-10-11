# How to Run

## Server
To start the server, run Server.jar by executing this command line:
```
java Server <port_number> <protocol>
```
Example:

For TCP:

```java Server 1500 TCP```

For UDP:

```java Server 1500 UDP```


## Client
To start the client, run Client.jar by executing this command line:

```
java Client <port_number> <host_address> <protocol>
```


Example:

For TCP:

```java Client 1500 localhost TCP```

For UDP:

```java Client 1500 localhost UDP```

## Command accept by client

The character ',' is used as a delimiter in these comma, and thus K-V key or value should not contain the comma. Commands accepted by client:

### Put command:  ```Put,<key>,<value>```

Example: ```Put,Banana,Yellow```   

### Get command: 
```Get,<key>```

Example: ```Get,Banana```

### Delete command:
```Delete,<key>```

Example: ```Delete,Banana```