# Acesso à Reserva na Companhia Aérea - Scraper e API

## Descrição

**Airline Reservation Access** é uma aplicação que permite raspar informações de reservas de voos a partir do site [VoarFácil](https://voarfacil.net/view/eticket/), utilizando uma chave específica. As informações extraídas serão armazenadas em um banco de dados e estarão disponíveis para serem consumidas por clientes HTTP.

## Uso

### Requisitos

Certifique-se de ter os seguintes requisitos instalados:

- Java 17
- Banco de Dados (por exemplo, MySQL, PostgreSQL)
- Docker (opcional, para facilitar o setup)

### Configuração

1. Clone o repositório:

   ```bash
   git clone https://github.com/seu-usuario/AirlineReservationAccess.git
   ```

2. Configure o banco de dados:

   - Crie um banco de dados para a aplicação.
   - Atualize as configurações de banco de dados no arquivo `application.properties`.

3. Execute a aplicação:

   ```bash
   ./gradlew bootRun
   ```

   ou

   ```bash
   gradlew bootRun
   ```

4. Acesse a API:

   - Acesse [http://localhost:8080/api/reservations/{chave}](http://localhost:8080/api/reservations/{chave})
     substituindo `{chave}` pela chave específica da reserva.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- HTMLUnit (para web scraping)
- Banco de Dados (MySQL, PostgreSQL, etc.)

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir problemas (issues) ou enviar pull requests.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
