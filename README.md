# Backend do projeto BeerStore

Build da imagem:

```
docker build -t normandesjr/java-memory:0.1 .
```

Executando o container:

```
docker run -p 8080:8080 -it --rm -m 1024M normandesjr/java-memory:0.1
```

Iniciando o Prometheus para métricas
```
docker run --rm -v ~/dev/beerstore/prometheus.yml:/etc/prometheus/prometheus.yml --network host --name prometheus  prom/prometheus
```

Query: 
```
sum (jvm_memory_max_bytes / 1000000) by (area)
```

```
sum (jvm_memory_used_bytes / 1000000) by (area)
```

