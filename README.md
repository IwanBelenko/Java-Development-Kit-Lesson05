# Java-Development-Kit-Lesson05

 Класс Fork симулирует вилку, которую могут поднимать или опускать философы.
 Вилка реализована как объект блокировки Lock для обеспечения взаимного исключения.
 Класс Philosopher представляет философа, который пытается поднять обе вилки, чтобы поесть.
 Сначала он пытается поднять левую вилку, а потом, если это ему удаётся, он пытается поднять правую вилку.
 Если поднять правую вилку не удаётся, он кладёт обратно левую и пробует позже.
 Главный класс DiningPhilosophers создаёт пять философов и пять вилок, и начинает действие, создавая потоки для каждого философа.
 Каждый философ будет пытаться есть до тех пор, пока не съест три тарелки спагетти, после чего прекратит свою деятельность.


 
 Результат работы в терминале

 ![Image alt](https://github.com/IwanBelenko/Java-Development-Kit-Lesson05/blob/main/Снимок%20экрана%202024-01-24%20в%2018.26.21.png)
