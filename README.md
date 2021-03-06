# ParserMOEX
<h2>Инструкция по запуску</h2>

<b>1.</b> Необходимо загрузить на компьютер файлы проекта
<ul>
  <li>выбрать папку для размещения проекта (например, C:/Desktop/)</li>
  <li>в командной строке ввести <blockquote>git clone https://github.com/TimurShubin/ParserMOEX</blockquote> или скачать ZIP архив с страницы проекта</li>
</ul>
<b>2.</b> Необходимо развернуть проект
<ul>
  <li>Зайти в IDE, с которой вы обычно работаете.</li>
  <li>В меню проекта, выбрать "Импорт проекта" и указать папку в которую ранее был размещен проект (там размещен файл pom.xml)</li>
  <li>после импорта проекта запустить проект в IDE</li>
  <li>Далее в командной строке переместиться в папку с проектом и указать команду <blockquote>npm install</blockquote> (в папке размещен файл package.json)</li>
  <li>после установки пакетов в командной строке указать команду <blockquote>npm start</blockquote></li>
</ul>
<b>3.</b> Далее, после запуска сервера, приложение должно работать.

<h2>Архитектура MVC</h2>

<b>1. Model</b>
<ul>
  <li>Trade - модель, содержит поля по сделкам</li>
  <li>Emitent - модель, содержит поля по эмитентам</li>
</ul>
<b>2. View</b>
<ul>
  <li>TradeEmitent - модель, содержит смешанные поля для отображения в таблице истории сделок</li>
</ul>
<b>3. Controller</b>
<ul>
  <li>AppController - контроллер, обрабатывает события, приходящие от клиента, передает данные на сервисный слой</li>
</ul>
<ul>
  <li>Сервисный слой состоит из:</li>
  <li>History - файл, содержащий код для CRUD операций c данными</li>
  <li>XmlReader - файл, содержащий код для обработки входящих XML файлов</li>
</ul>
<ul>
  <li>Слой для обработки данных в БД состоит из:</li>
  <li>EmitentRepository - файл, отвечает за обработку данных об эмитенте</li>
  <li>TradeRepository - файл, отвечает за обработку данных о сделках</li>
</ul>
<p>В данном проекте используется H2 Database</p>
