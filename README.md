# Buscador de CEP

Um aplicativo Java que permite consultar endereços a partir de CEPs e vice-versa, utilizando a API ViaCEP.

## 🚀 Tecnologias Utilizadas

- Java 17
- Google Gson (para manipulação de JSON)
- API ViaCEP
- Java HTTP Client

## 📋 Funcionalidades

O aplicativo oferece duas funcionalidades principais:

1. **Busca de Endereço por CEP**
   - Permite consultar um endereço completo a partir de um CEP
   - Gera um arquivo JSON com os dados do endereço encontrado

2. **Busca de CEP por Endereço**
   - Permite encontrar um CEP a partir de UF, cidade e logradouro

## 💻 Como Usar

### Pré-requisitos

- Java 17 ou superior instalado
- Conexão com a internet

### Executando o Programa

1. Compile os arquivos Java:
```bash
javac src/*.java
```

2. Execute o programa:
```bash
java -cp src Main
```

### Exemplos de Uso

#### Buscando Endereço por CEP

1. Selecione a opção 1 no menu
2. Digite o CEP (apenas números)
3. O programa retornará os dados do endereço e gerará um arquivo JSON

Exemplo de entrada:
```
01001000
```

Exemplo de saída:
```
Endereco[cep=01001-000, logradouro=Praça da Sé, localidade=São Paulo, complemento=lado ímpar, uf=SP]
```

#### Buscando CEP por Endereço

1. Selecione a opção 2 no menu
2. Digite a UF (sigla do estado)
3. Digite o nome da cidade
4. Digite o logradouro
5. O programa retornará o CEP correspondente

Exemplo de entrada:
```
UF: SP
Cidade: São Paulo
Logradouro: Avenida Paulista
```

Exemplo de saída:
```
Endereco[cep=01310-100, logradouro=Avenida Paulista, localidade=São Paulo, complemento=, uf=SP]
```

## 📝 Estrutura do Projeto

- `Main.java`: Classe principal com a interface do usuário
- `ConsultaCep.java`: Classe responsável pela consulta de endereços via API
- `ConsultaEndereco.java`: Classe responsável pela consulta de CEPs via API
- `Endereco.java`: Record que representa a estrutura de um endereço
- `GeradorDeArquivo.java`: Classe responsável por gerar arquivos JSON com os resultados

## ⚠️ Observações

- O CEP deve ser digitado apenas com números
- A UF deve ser digitada com duas letras (ex: SP, RJ, MG)
- É necessário ter conexão com a internet para realizar as consultas
