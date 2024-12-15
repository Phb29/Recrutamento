🚀 Instruções para Rodar o Projeto
🛠 Backend
Para rodar o backend usando Docker, siga os passos abaixo:

Abra o terminal na pasta RecrutamentoInterno (diretório raiz do backend).

Compile o projeto com Maven (pulando os testes para evitar erros)
com comando:  mvn clean package -DskipTests 

essa etapa de rodar o comando mvn clean package -DskipTests  é so para garantir  q não de erro ao construir a aplicação do docker
logo depois 
Suba os containers com Docker Compose:

docker-compose up --build

Isso irá garantir que o Docker construa e execute a aplicação sem erros.

🎨 Frontend
Para rodar o frontend, siga os passos abaixo:

Abra o projeto RecrutamentoInternoWeb na sua IDE de preferência.

No terminal da IDE, instale as dependências com o comando:
npm install

Certifique-se de ter instalado as seguintes versões de ferramentas:

Angular CLI: 8.3.29
Node.js: 10.13.0

Após a instalação das dependências, rode o projeto com:

ng serve -o

O comando acima irá iniciar a aplicação Angular e abrir a aplicação no navegador.

💡 Sobre o Projeto
O projeto é um sistema de Recrutamento Interno que permite gerenciar candidatos e vagas. Veja como funciona:

👤 Login
Ao acessar a página inicial, você pode escolher entre dois tipos de login:

Recrutador
Candidato
Caso não tenha uma conta, você poderá criar um novo login.

🎯 Painel do Candidato
No painel do candidato, é possível:

Ver a lista de vagas disponíveis.
Aplicar nas vagas desejadas.
📝 Painel do Recrutador
No painel do recrutador, é possível:

Ver notificações dos candidatos que aplicaram às vagas criadas.
Criar novas vagas.
Visualizar a lista de candidatos cadastrados.
Excluir vagas existentes.

⚠️ Observação Importante
🔒 Segurança: Eu sei que é uma boa prática não expor tokens e informações sensíveis do banco no repositório público. Normalmente, isso é feito utilizando arquivos .env e adicionando-os ao .gitignore.

Mas, para facilitar o teste do projeto por quem irá analisá-lo, eu deixei as configurações de acesso expostas no código.

qualquer duvida para rodar o projeto ou sobre o projeto meu contato (62)994278721
