# Backend do projeto BeerStore

Aplicação BeerStore para várias demonstrações.

## Demonstração do uso de memória

A aplicação serve para várias demonstrações, uma delas é para mostrar como a aplicação se comporta em relação a memória em um container.

### Vagrant

Para simularmos um servidor com qualquer quantidade de memória, usaremos o Vagrant. Acesse o diretório virtual-machine execute o comando abaixo:

```
vagrant up
```

Ele irá iniciar uma máquina virtual centos/7 e executará o ansible para instalar o Docker.

Para acessar a máquina virtual via ssh, execute o comando abaixo no mesmo diretório:

```
vagrant ssh
```

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
docker image load -i /vagrant/beerstore.tar.gz
```

### Para executar o container dentro da VM:

```
docker run -p 8080:8080 -m 150M --memory-swap 300M --rm normandesjr/beerstore:memorydemo
```

Repare no Prometheus que a aplicação (http://localhost:8080/beers/load) aloca uns 250MB de memória, mas como?

Como temos 300M pro container (memória + swap), tudo bem, deu certo! Conseguimos executar e provisionar um tanto bom de cerveja!

Mas ficamos com medo, e tentaremos aumentar a memória da máquina e do container, será que resolve?

Para isso, provisionaremos uma máquina virtual com mais memória, 8GB e colocaremos o container com 600M.

Altere o Vagrantfile para suportar 8192MB (8GB), reprovisione a VM e suba o container com 600M de memória. 

```
docker run -p 8080:8080 -m 600M --memory-swap 800M -d normandesjr/beerstore:memorydemo
```

Tente carregar cervejas agora (http://localhost:8080/beers/load)

Nem conseguiremos, a aplicação já recebe um kill do container

```
docker inspect <container id ou nome> | grep -i oom
"OOMKilled": true,
```

Agora a JVM tentará alocar por volta de 2GB de memória, mas só tem 800M disponível!

Para passar parâmetro pra JVM, use -e JAVA_OPTIONS='-Xmx300m'

### Prometheus

Usamos o Prometheus para monitorar a JVM. Para iniciar, execute o comando abaixo:

```
docker run --rm -v ~/dev/beerstore/prometheus.yml:/etc/prometheus/prometheus.yml --network host --name prometheus  prom/prometheus
```

E adicione as seguintes queries:

```
sum (jvm_memory_max_bytes{area="heap"} / 1000 / 1000)
```

```
sum (jvm_memory_committed_bytes{area="heap"} / 1000 / 1000)
```
