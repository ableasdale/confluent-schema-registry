# confluent-schema-registry

Start up the components:

```bash
docker-compose up -d
```

Sanity test Zookeeper:

Let's check the Zookeeper Shell on the Zookeeper host:

```bash
docker-compose exec zookeeper zookeeper-shell localhost:2181
```

```bash
get /controller
{"version":2,"brokerid":1,"timestamp":"1689018188126","kraftControllerEpoch":-1}
```



