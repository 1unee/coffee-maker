Сервис представляет собой обобщение работы с материалами и продуктами. 
Частным случаем является кофе.

1. (*[github](https://github.com/1unee/laboratoy-work-4.git)*)
2. Условие работы представлено в директории `src/main/resources/static/task.docx`.
3. Описание API: __[OPEN API DOCS](http://localhost:8087/swagger-ui/index.html#/)__

-------------------------------------

SYSTEM DESIGN:
1. Заказ делается примерно каждые две минуты.
2. Статистика хранится в течение 5 лет.

При заказе формируется сущность оплаты, 
которая примерно занимает 8 + 8 + 16 + 4 + 1 + ~200 = 237 байт.

Заказ делается каждые две минуты => 237 * 30 = 7110 байт = 7 Мб за час
7 * 24 * 365 = 61 320 Мб = 60 Гб в год 
Таким образом под хранение статистики нужно закупать сервер(а)
объемом не менее 350-400 Гб, чтобы брать с небольшим запасом

Заказ делается относительно пропускных способней HDD очень редко,
поэтому берем самые дешевые HDD
