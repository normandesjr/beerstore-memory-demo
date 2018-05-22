# Backend do projeto BeerStore

Aplicação BeerStore para várias demonstrações.

## Demonstração do uso de memória

A aplicação serve para várias demonstrações, uma delas é para mostrar como a aplicação se comporta em relação a memória em um container.

### Processo de build e carregamento da imagem

Se precisar fazer alguma alteração e queira fazer um novo build da imagem, use o comando abaixo:

```
docker build -t normandesjr/beerstore:memorydemo .
```

Assim é possível exportar a imagem para uso na VM, para isso utilize o comando abaixo:

```
docker image save -o virtual-machine/beerstore.tar.gz normandesjr/beerstore:memorydemo
```

Então dentro da VM, é possível carregá-la:

```
sudo docker image load -i /vagrant/beerstore.tar.gz
```

### Para executar o container dentro da VM:

```
docker run -p 8080:8080 -d -m 150M --memory-swap 300M normandesjr/beerstore:memorydemo
```

### Prometheus

Usamos o Prometheus para monitorar a JVM. Para iniciar, execute o comando abaixo:

```
docker run --rm -v ~/dev/beerstore/prometheus.yml:/etc/prometheus/prometheus.yml --network host --name prometheus  prom/prometheus
```

E adicione as seguintes queries:

```
sum (jvm_memory_max_bytes / 1000 / 1000) by (area)
```

```
sum (jvm_memory_committed_bytes / 1000 / 1000) by (area)
```
