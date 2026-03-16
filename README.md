# Formação Java Web Fullstack

Este repositório contém os materiais e projetos desenvolvidos como parte da **Formação Java Web Fullstack**, um curso abrangente que visa capacitar desenvolvedores na construção de aplicações web robustas utilizando as principais tecnologias do ecossistema Java. O curso aborda desde conceitos fundamentais de persistência de dados com ORM até o desenvolvimento de interfaces de usuário dinâmicas com JSP e JSF.

## Visão Geral do Curso

A formação é dividida em módulos, cada um focado em uma tecnologia ou conceito específico, permitindo um aprendizado progressivo e aprofundado. Os módulos são projetados para serem práticos, com exemplos de código e projetos que demonstram a aplicação dos conhecimentos adquiridos.

## Módulos da Formação

Atualmente, a formação inclui os seguintes módulos:

### 1. ORM com JPA e Hibernate

*   **Descrição**: Este módulo foca na persistência de dados utilizando **Hibernate** como framework ORM (Object-Relational Mapping) e JPA (Java Persistence API). Ele aborda a integração com bancos de dados relacionais, mapeamento de entidades, e operações CRUD (Create, Read, Update, Delete).
*   **Tecnologias Chave**: Java, Hibernate, JPA, MySQL, Maven.
*   **Localização**: `orm-hibernate/orm-hibernate`

### 2. JSP (JavaServer Pages)

*   **Descrição**: O módulo de **JSP (JavaServer Pages)** explora o desenvolvimento de interfaces web dinâmicas. Inclui exemplos práticos de como criar páginas JSP, integrar com servlets para lógica de controle, usar expressões JSP, e aplicar boas práticas de separação entre lógica e apresentação. Tópicos avançados como geração de relatórios em PDF com JasperReport Studio e visualização de gráficos com Chart.js também são abordados.
*   **Tecnologias Chave**: Java, JSP, Servlets, JasperReport Studio, Chart.js, Maven.
*   **Localização**: `curso-jsp`

### 3. JSF (JavaServer Faces)

*   **Descrição**: O módulo de **JSF (JavaServer Faces)** oferece uma base sólida para a construção de aplicações web com interfaces ricas e interativas. Abrange desde a configuração inicial de projetos JSF, uso de Managed Beans, navegação de páginas, formulários e validação, até a integração com banco de dados e exibição dinâmica de dados. A integração com PrimeFaces para componentes visuais avançados também é demonstrada.
*   **Tecnologias Chave**: Java, JSF, Managed Beans, PrimeFaces, Maven.
*   **Localização**: `curso-jsf`

## Como Utilizar este Repositório

Cada módulo é um projeto Maven independente. Para explorar um módulo específico:

1.  **Navegue até o diretório do módulo**: Por exemplo, para o módulo Hibernate, vá para `orm-hibernate/orm-hibernate`.
2.  **Compile o projeto**: Utilize o Maven para compilar o projeto e baixar as dependências:
    ```bash
    mvn clean install
    ```
3.  **Configure o Banco de Dados (se aplicável)**: Para módulos que interagem com banco de dados (como ORM Hibernate), certifique-se de ter um servidor MySQL em execução e configure as credenciais no arquivo `persistence.xml` (ou equivalente).
4.  **Execute os Exemplos**: Cada módulo geralmente contém uma classe `Test.java` ou um ponto de entrada similar para demonstrar as funcionalidades. Consulte o README específico de cada módulo para instruções detalhadas de execução.

## Contribuição

Contribuições são bem-vindas! Se você encontrar erros, tiver sugestões de melhoria ou quiser adicionar novos exemplos, sinta-se à vontade para abrir uma issue ou enviar um pull request.
