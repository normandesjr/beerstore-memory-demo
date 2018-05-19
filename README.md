# Backend do projeto BeerStore

Build da imagem:

```
docker build -t normandesjr/java-memory:0.1 .
```

Executando o container:

```
docker run -p 8080:8080 -it --rm -m 1024M normandesjr/java-memory:0.1
```

```
docker run  --rm -v ~/dev/beerstore/prometheus.yml:/etc/prometheus/prometheus.yml --network host  prom/prometheus
```