package ru.otus.java.pro.mt.core.transfers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreTransfersApplication {

     /*
	Домашнее задание 1:
	1. Изучить текущее состояние проекта
	2. В dev/environment добавьте docker-compose с поднятием Kafka
	3. После успешного исполнения перевода отправьте в кафку (в топик: "mt.transfers.status.info") сообщение вида:
	  {
	    "transferId": "..",
	    "status": "EXECUTED"
	  }
	4. Сделайте  "мини-сервис" нотификаций, который вычитывет топик кафки из п. 4 и выводит в лог сообщение
	"По переводу ${id} клиенту отправлена нотификация"

	Домашнее задание 2:
	1. Создайте отдельный класс для хранение настроек RestClient (url, readTimeout, connectTimeout)
	2. Создайте бин-фабрику, которая по объекту из п.1 позволяет построить объект типа RestClient
	3. При создании RestClient добавьте настройку readTimeout/connectTimeout (пока при создании используется только url)
	4. В RestClientsConfig создавайте RestClient через полученную фабрику
	5. Покройте openapi описанием все контроллеры и дто, которые используете

    Домашнее задание 3:
    - Достройте dev/environment таким образом, чтобы через docker-compose запускались все сервисы,
    и все необходимое окружение (БД, ELK, Prometheus/Grafana)
    - Добавьте в мс переводов 3 метрики: Получено запросов на выолнение перевода, Кол-во успешных переводов,
    количество неуспешных переводов
    - Добавьте пагинацию к запросу списка переводов, чтобы при запросе можно указать размер
    (по-умолчанию 20, максимально 1000) и номер страницы
    */


    public static void main(String[] args) {
		SpringApplication.run(CoreTransfersApplication.class, args);
	}
}
