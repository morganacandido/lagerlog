# Lagerlog
Descrição:
Sistema de administração de vendas.
Em resumo o sistema é capaz de realizar um relatório de vendas, administração de estoque, cadastrar produtos. 

## Requisitos
* Java JDK 17
* Maven ou extensões Spring Boot Dashboard para utilização em VS Code
 
## Rodando o projeto com Maven
Instalando dependências
```
mvn clean install
```

Iniciando o servidor
```
java -jar target/lagerlog-0.0.1-SNAPSHOT.jar
```

O projeto será iniciado no localhost
```
localhost:8080/
```

## Como trabalhar com o repositório forkado do GitHub no VS Code

### Passos para clonar e configurar o projeto (itens 1, 2 e 3)

### 1. Fazer Fork do Repositório
1. Acesse o repositório original no GitHub.
2. Clique no botão **Fork** no canto superior direito da página. Isso criará uma cópia do repositório original na sua conta do GitHub.

### 2. Clonar o Repositório forkado localmente
1. Na sua conta, vá até o repositório forkado no GitHub e clique no botão **Code**.
2. Copie a URL do repositório forkado (exemplo `https://github.com/seu-usuario/nome-do-repositorio.git`).
3. Abra o Visual Studio Code e o terminal integrado (pressione `Ctrl+'`).
4. Navegue até o diretório onde deseja clonar o repositório:
   ```bash
   cd /caminho/para/diretorio
   ```
5. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/nome-do-repositorio.git
   ```
6. Entre no diretório clonado:
   ```bash
   cd nome-do-repositorio
   ```

### 3. Configurar o Repositório Remoto "Upstream"
1. Adicione o repositório original como um repositório remoto chamado `upstream`:
   ```bash
   git remote add upstream https://github.com/usuario-original/nome-do-repositorio.git
   ```
2. Verifique se o remote foi configurado corretamente:
   ```bash
   git remote -v
   ```
   O comando deve retornar algo como:
   ```
   origin    https://github.com/seu-usuario/nome-do-repositorio.git (fetch)
   origin    https://github.com/seu-usuario/nome-do-repositorio.git (push)
   upstream  https://github.com/usuario-original/nome-do-repositorio.git (fetch)
   upstream  https://github.com/usuario-original/nome-do-repositorio.git (push)
   ```

## Modificações e atualizações do código no GitHub via VS Code
1. Abra o projeto no Visual Studio Code:
   ```bash
   code .
   ```
2. Faça as modificações no código conforme necessário.
3. Verifique o status das modificações:
   ```bash
   git status
   ```
4. Adicione os arquivos modificados ao commit:
   ```bash
   git add .
   ```
5. Faça o commit das alterações:
   ```bash
   git commit -m "Descrição das alterações"
   ```
6. Enviar alterações para o GitHub:**
  ```bash
  git push origin main
  ```

7. Buscar atualizações do repositório original:
  ```bash
  git fetch upstream
  ```

8. Mesclar atualizações do repositório original:
  ```bash
  git merge upstream/main
  ```

## Dicas Extras:
- Sempre mantENHA O repositório forkado atualizado com o repositório original para evitar conflitos.
- Utilize as mensagens de commit para detalhar as modificações feitas.

## Autores
* Davison Azevedo
* Rodrigo Moraes
* Morgana Candido
* Gabriel Baptista
* Augusto Cruz
