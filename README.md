# Java API Test Automation Example

[![Automação QA - Execução e Relatório](https://github.com/guilherme-evangelista/JavaAPIExample/actions/workflows/ci.yml/badge.svg)](https://github.com/guilherme-evangelista/JavaAPIExample/actions/workflows/ci.yml)
[![GitHub Pages](https://img.shields.io/badge/Relatório-Cucumber%20BDD-brightgreen?logo=cucumber)](https://guilherme-evangelista.github.io/JavaAPIExample/)

Projeto moderno de automação de testes de API focado em demonstrar boas práticas de engenharia de software e Qualidade (QA), construído inteiramente utilizando **Java**, **REST Assured** e focado na facilidade de negócio através de **Cucumber (BDD)**. Os cenários estão em conformidade e utilizam endpoints baseados nos dados em mocks providos pelo sand-box da [DummyJSON](https://dummyjson.com/).

## 🚀 Tecnologias e Ferramentas

* **Java 17**
* **REST Assured** - Biblioteca nativa para requisições e asserções na modelagem das APIs.
* **Cucumber** - Framework de automação BDD (Behavior-Driven Development).
* **TestNG** - Framework enxuto de suítes de testes e execução paralela.
* **Lombok & Jackson** - Serialização e desserialização robusta de *Data Transfer Objects* baseada em *POJOs* simplificados.
* **Maven** - Gerenciamento escalonável das dependências e builds.
* **Cluecumber Report** - Relatórios interativos HTML extraídos nativamente pelo Cucumber.
* **GitHub Actions & Pages** - Execução contínua de Integração CI/CD.

## ⚙️ Arquitetura do Projeto

A stack do projeto utiliza fortes distinções lógicas e contextualizações entre Endpoints vs Step Definitions baseadas na arquitetura padrão em testes corporativos robustos.

*   `src/test/resources/features/`: Regras de negócio e casos de uso de Automação mapeadas usando notações do Gherkin (BDD), com suporte ao nosso idioma (`#language: pt`).
*   `src/test/java/com/dummyjson/steps/`: As conhecidas *Step Definitions*. Camada em Java encarregada por agir com os cenários via *RegEx*. A persistência de dependências isoladas ou Respostas de API ao longo dessas classes é protegida a nível de Thread através do **`SharedContext.java`**.
*   `src/main/java/com/dummyjson/services/`: Concentra a criação e mapeamento lógico das Requests do REST Assured referentes a cada funcionalidade do negócio de forma isolada.
*   `src/main/java/com/dummyjson/data/models/`: Agrupa de forma categorizada por domínio os Modelos/Objetos (POJOs) usados tanto passivamente pelo Request, como retornados pelo Body.

## 📦 Como Executar e Ver Relatórios Localmente

### Pré-requisitos
1. Em seu Path sistêmico deve estar ativo pelo menos um **JDK (17 ou superior)**;
2. Instalação e mapeamento do **Maven** ativo via prompt de comando da máquina;
3. *IntelliJ IDEA* ou IDE semelhante para manipular arquivos do projeto à preferência pessoal;

Certifique-se que o repositório atual foi clonado em seu diretório de arquivos e está acessível.

### Rodando e Compilando a Suíte Padrão

Dentro da raiz do projeto, lance o test runner completo gerenciado pelo maven:
```bash
mvn clean test cluecumber-report:reporting
```
*Observação: A configuração da classe `RunTest.java` já abrange automaticamente todas as pastas e hooks pertinentes.*

### Visualizando o Cluecumber Report HTML
Junto ao final de sua execução pelo Maven demonstrado acima, o JSON do test runner original do cucumber fará sua conversão estática para as prateleiras em HTML via plugin Cluecumber.

No seu Explorador de Arquivos/IDE, acesse fisicamente abrindo os hiperlinks da raiz de `target`:
**`target/cluecumber-report/index.html`**

## 🔄 CI/CD (GitHub Actions Pipeline)

Nacionalmente integrado, o arquivo `.github/workflows/ci.yml` sustenta a pipeline contínua.
Os gatilhos ocorrem dinamicamente à qualquer mudança principal na root folder (`push`/`pull_request`) nas branches core **main/master**, além de possibilidade manual interna (**workflow_dispatch**).

A *Action* engloba a ativação da suíte em ambiente isolado no Ubuntu (Latest) montando o compilador e injetando dependências virtualmente, onde atua também a finalização da postagem pública visual dos relatórios gerados sendo expostos dentro do escopo do **GitHub Pages**, o que pode ser compartilhado como registro visual com o restante das squads técnicas em tempo real!
