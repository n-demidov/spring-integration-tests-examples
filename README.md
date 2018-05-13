Just for internal purposes

Черновой набросок плана:
1. Декомпозиция/дизайн классов
2. Dev-тесты
    - 2.1 Определения
    - 2.2 Советы совсем начинающим
    - 2.3 Плюсы dev-тестов (юнит-тесты и интеграционные)
    - 2.4 Совмещение юнит-тестов с интеграционными
    - 2.5 Рекомендуемые материалы и доки

#### 1. Декомпозиция классов

##### а) Декомпозируем на множество маленьких классов, с единственным public-методом.

Получится что-то типо чистой функции (детерминированная, без побочных эффектов).

При необходимости, такую "функцию" будет легче покрыть юнит-тестами с различными вариантами входных параметров и пр.

Этот маленький класс, представляющий один конкретный функционал - занимается только одной единственной задачей. Этот принцип называется "single responsibility" (первый принцип из SOLID).

###### Детерминированная = возвращает один и тот же результат на одни и те же входные параметры.


##### б) Эти маленькие классы можно использовать в разных местах как конструктор.

Код будет больше похож на конструктор, из которого будет легче "собирать" основную логику приложения: из классов (малюсеньких функций-примитивов) другие классы составляют бизнес-логику (бизнес-функции) (типо классов-фасадов/медиаторов и пр.).

Например:
- можно вынести из сложного класса кусок функционала с вычислением в отдельный мелкий класс и покрыть его юнит-тестами.
- а еще можно сделать что-то типо класса фасада, который будет агрегировать все эти мелкие классы и выстраивать логику с помощью них.

#### Dev-тесты

##### 2.1. Определения
Под юнит тестом понимается тест, который проверяет какой-либо один метод, либо один класс.

Под интеграционным тестом - тест, который проверяет интеграцию между несколькими классами или слоями (приложения).

Под TDD - просто написание тестов раньше кода.

##### 2.2. Советы совсем начинающим

<details>
  <summary>Подразумевается, что слушатель уже пишет юнит и интеграционные тесты, знаком с TDD. Если нет - раскройте спойлер...</summary>
  Если не знакомы с написанием юнит-тестов, то рекомендую почитать разные статьи/видео на YouTube на эту тему. В том числе как пользоваться JUnit, например. И практиковаться, например, написать конвертер из арабских чисел в римские; и покрыть юнит-тестов.

Далее ознакомиться с Mockito и внедрить в существующий проект.

Затем ознакомиться с TDD: пробежать статьи. А также по TDD есть примеры различных видео на YouTube.

Например, для быстрого старта по TDD - https://www.youtube.com/watch?v=K-eA9ZIkJBg&list=PLUA2W_Brp86uVqJVhLQRdhnSsmmWpnyhe&t=55m14s

С интеграционными-тестами сложнее:
- Туториал "Integration Testing in Spring" - http://www.baeldung.com/integration-testing-in-spring
- Туториал для Spring Boot тестов можно поискать тут - https://spring.io/guides
</details>

##### 2.3. Плюсы dev-тестов (юнит-тесты и интеграционные)

Когда разработчик написал код, то в подавляющем большинстве случаев он его будет тестить. То есть в каком-то смысле, dev-тест - входит часть разработки. 

С тестами качество разработки возрастает на порядок.

Плюсы от наличия тестов разработчику (то есть при рефакторинге, добавлении/изменении функционала, фиксах):
- удобное и быстрое воспроизведение долго/трудно воспроизводимого кейза (если имеется наличие множества зафиксированных кейзов для воспроизведения);
- проверка на регрессии. Также при фиксе можно добавить в тест новое условие;
- наличие "живой документации" того, как должен работать класс/код (если понятно написан);
- выполняются автоматизированно, а не руками (рутинно);
- легче и приятнее писать. Т.к. в случае отсутствия тестов приходится анализировать код и искать/восстанавливать спецификацию, по которой он был написан;

###### Примеры:
<details>
  <summary>Пример, когда интеграционные тест многократно ускоряет проверку функционала...</summary>
Допустим, у меня имеется websocket-сервер. По бизнес требованию, если пользователь был не активен более 15 минут, то сервер должен делать дисконнект. У меня написан интеграционный тест, в котором "замокано" время ожидания с 15 минут до нескольких миллисекунд. В связи с чем кейз воспроизводится очень быстро. Плюс мы имеем наличие проверки на регресс (и автоматический запуск при каждом билде приложения).
</details>

###### Примеры:
* Для тестирования рокировки руками в сетевых шахматах пришлось бы запустить сервер и два клиента. Затем залогиниться двумя игроками, создать между ними игру, выдвинуть фигуры вперёд и далее только тестировать рокировку. Вероятней всего, воспроизводить потребуется несколько раз (разные кейзы рокировки).

Плюсы от TDD:
- поощрение писать лучше спроектированный код:
  - позволяет поработать с API своего кода на практике, в боевых условиях;
  - поощрение писать чистые функции: их легче покрывать тестами и они читаются легче;
  - улучшение API отдельных классов: сразу видно API класса. Удобно ли использовать класс. Выделяешь новые сущности.
  - улучшение структуры кода (связей классов/архитектуры): подстегивает разносить всё по разным классам. Объединяя их в классах-фасадах/медиаторах и пр. Таким образом может быть легче прочитать логику кода;
  - даже если планируешь писать тесты после кода, то все равно задумываешься над тем как будешь их писать => постараешься лучше спроектировать;
- сразу пишешь правильно работающий код;
- остаются тесты;
- гораздо труднее "впилить" тесты в существующий код, т.к. скорее всего придется переделывать код и есть бОльшие опасности его поломать, т.к. в данный момент там нет тестов;

Плюсы для бизнеса (если разрабы будут писать dev-тесты):
- уменьшают время разработки (если разрабы умеют хорошо писать тесты. В самом начале тесты будут притормаживать написание проекта с нуля);
- при наличии хороших дев-тестов проект будет легче саппортить (как старым так и новым прогерам);
- в продакшене будет меньше багов. В том числе регрессий;
- меньше прохождения циклов Dev -> QA -> Dev, т.к. меньше багов в Dev за счёт наличия dev-тестов;


##### 2.4. Совмещение юнит-тестов с интеграционными

Юнит-тесты тестируют один класс или один метод.
А интеграционные тесты тестируют интеграцию между несколькими классами или слоями приложения. То есть сразу все классы.

Юнит-тесты выполняются быстро. Ими можно покрывать широкий спектр условий. Их хорошо писать, когда код не линеен.

Интеграционные тесты выполняются долго, т.к. как правило поднимают весь (почти весь) контекст приложения. Их хорошо писать, когда код линеен.

Модель совмещения юнит-тестов с интеграционными может выглядеть как:
- Когда код не линеен - его можно вынести в отдельный класс (с единственным паблик-методов) и покрыть юнит-тестами.
- Код, вызывающий класс выше, снова станет линейным.
- И затем написать интеграционный тест. Код, вызывающий класс выше, проверится этим интеграционным тестом...

Ну и как вывод: необходимость писать dev-тесты будет подталкивать делать лучше пункт 1 - дизайн классов.


##### 2.5 Рекомендуемые материалы и доки

Рекомендую следующие 2 видео:
  - Hexlet - "Webinar #5: Тестирование и TDD" - https://www.youtube.com/watch?v=DqOnfQ4Ad5s
  - Николай Алименков "Сага о том, как Java-разработчики должны тестировать свои приложения" - https://www.youtube.com/watch?v=K-eA9ZIkJBg

###### Info:
К юнит-тестам:
- Библиотека Pragmatists (библиотека упрощает написание юнит-тестов, если имеется много вариантов входных параметров) - https://github.com/Pragmatists/junitparams/wiki/Quickstart
- Библиотеку AssertJ:
  - описание - https://joel-costigliola.github.io/assertj/
  - подключить в мавен - https://joel-costigliola.github.io/assertj/assertj-core-quick-start.html

К интеграционным-тестам:
- Туториал "Integration Testing in Spring" - http://www.baeldung.com/integration-testing-in-spring
- Spring Testing docs - https://docs.spring.io/spring/docs/current/spring-framework-reference/testing.html


