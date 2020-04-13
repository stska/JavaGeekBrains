package com.company;
/*
Практическое задание
-Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса. Эти классы должны уметь бегать и прыгать
    (методы просто выводят информацию о действии в консоль).
-Создайте два класса: беговая дорожка и стена, при прохождении через которые, участники должны выполнять соответствующие действия (бежать или прыгать),
    результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
-Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.
-* У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения на бег и прыжки. Если участник не смог пройти одно из препятствий,
    то дальше по списку он препятствий не идет.
 */

public class Main {

    public static void main(String[] args) {

	  Object [] rivels = new Object[4];
	  rivels[0] = new Cat("Barsik",2);
	  rivels[1] = new Robot("RoboRoboMan",120);
	  rivels[2] = new Human("MadMax",31);
	  rivels[3] = new Cat("ChubbyPie",4);

	  Object [] track = new Object[5];
	  track[0] = new Wall();
	  track[1]= new Wall();
	  track[2] = new TreadMill();
	  track[3] = new TreadMill();
	  track[4] = new Wall();

    //проходимся по массиву участников
	  for(int i = 0; i < rivels.length; i++){
	      //проходимся по массиву препятсвий
          for(int j = 0; j < track.length; j++){
              //проверяем Кот ли это, если да про из Object приведение типов приводим к Cat
              if(rivels[i] instanceof Cat){
                  Cat cat = (Cat)rivels[i];        //из Object -> Cat
                  //проверяем какой препятсвие дистанция на дорожке или стена
                  if(track[j] instanceof TreadMill){
                      TreadMill treadmill = (TreadMill) track[j]; // из Object -> TreadMill
                      //вызываем метод run у объекта treadmill и передаем в качестве параметров  беговые характеристики и имя обЪкта
                      if(treadmill.run(cat.getRunAbility(),cat.getName())){
                          continue;
                      } else {
                          //выходим из текущего цикла полосы препятсвий и возвращаемся к участникаам
                          System.out.println("Прохождение полосы препятствий остановлено");
                          break;
                      }
                  } else {
                      Wall wall = (Wall) track[j];
                      if(wall.jump(cat.getJumpAbility(),cat.getName())){
                          continue;
                      } else {
                          System.out.println("Прохождение полосы препятствий остановлено");
                          break;
                      }
                  }
              } else if(rivels[i] instanceof Robot){
                    Robot robot = (Robot) rivels[i];
                  if(track[j] instanceof TreadMill){
                      TreadMill treadmill = (TreadMill) track[j];
                      if(treadmill.run(robot.getRunAbility(),robot.getName())){
                          continue;
                      } else {
                          System.out.println("Прохождение полосы препятствий остановлено");
                          break;
                      }
                  } else {
                      Wall wall = (Wall) track[j];
                     if(wall.jump(robot.getJumpAbility(),robot.getName())){
                         continue;
                     }else {
                         System.out.println("Прохождение полосы препятствий остановлено");
                         break;
                     }
                  }

              } else {
                  Human human = (Human) rivels[i];
                  if(track[j] instanceof TreadMill){
                      TreadMill treadmill = (TreadMill) track[j];
                      if(treadmill.run(human.getRunAbility(),human.getName())){
                          continue;
                      }else {
                          System.out.println("Прохождение полосы препятствий остановлено");
                          break;
                      }
                  } else {
                      Wall wall = (Wall) track[j];
                      if(wall.jump(human.getJumpAbility(),human.getName())){
                          continue;
                      }else {
                          System.out.println("Прохождение полосы препятствий остановлено");
                          break;
                      }
                  }
              }


          }
          System.out.println("-----------------------------");
      }

    }
}
