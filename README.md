# Confluent Schema Registry

Start up the components:

```bash
docker-compose up -d
```

## Component Check

### Zookeeper

Let's check the Zookeeper Shell on the Zookeeper host:

```bash
docker-compose exec zookeeper zookeeper-shell localhost:2181
```

```bash
get /controller
{"version":2,"brokerid":1,"timestamp":"1689018188126","kraftControllerEpoch":-1}
```

### ReST Proxy

```bash
curl -s -XGET localhost:8082/v3/clusters | jq
```

```bash
curl -s -XGET localhost:8082/topics | jq

```

### Schema Registry

```bash
curl -s -XGET http://localhost:8081/schemas/types | jq
```
