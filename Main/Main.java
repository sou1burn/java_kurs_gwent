package Main;

import Controller.GameController;
import Controller.MenuController;
import VIew.*;
import Model.*;

public class Main {
    public static void main(String[] args) {
        
        Deck nilfs = new Deck("Нильфгаард");
        nilfs.addCard(new Card(2, "mid", "Альбрых", "src/nilfs/albrix.png"));
        nilfs.addCard(new Card(6, "mid", "Ассире вар Анагыд", "src/nilfs/assire.png"));
        nilfs.addCard(new Card(5, "longRange", "Огненный скорпион", "src/nilfs/firescorp.png"));
        nilfs.addCard(new Card(6, "mid", "Фрингилья де Виго", "src/nilfs/fringilia.png"));
        nilfs.addCard(new Card(15, "melee", "Геральт из Ривии", "src/nilfs/geralt.png"));
        nilfs.addCard(new Card(6, "melee", "Кагыр", "src/nilfs/kagir.png"));
        nilfs.addCard(new Card(10, "melee", "Лето из Гулеты", "src/nilfs/leto.png"));
        nilfs.addCard(new Card(10, "mid", "Лучний Бурой Хоругви", "src/nilfs/luchnik.png"));
        nilfs.addCard(new Card(3, "melee", "Мортейзен", "src/nilfs/morteizen.png"));
        nilfs.addCard(new Card(3, "longRange", "Сгнившая петрария", "src/nilfs/petraria.png"));
        nilfs.addCard(new Card(3, "mid", "Путткамер", "src/nilfs/puttkamer.png"));
        nilfs.addCard(new Card(4, "melee", "Раинфарн", "src/nilfs/rainfarn.png"));
        nilfs.addCard(new Card(5, "melee", "Эмиель Регис", "src/nilfs/regis.png"));
        nilfs.addCard(new Card(5, "mid", "Ренуальд аэп Матсен", "src/nilfs/renuald.png"));
        nilfs.addCard(new Card(6, "longRange", "Сапер", "src/nilfs/saper.png"));
        nilfs.addCard(new Card(2, "mid", "Свирс", "src/nilfs/svirs.png"));
        nilfs.addCard(new Card(10, "melee", "Тибор Эггебархт", "src/nilfs/tibor.png"));
        nilfs.addCard(new Card(7, "melee", "Трисс Меригольд", "src/nilfs/triss.png"));
        nilfs.addCard(new Card(4, "mid", "Вангемар", "src/nilfs/vangemar.png"));
        nilfs.addCard(new Card(6, "melee", "Весимир", "src/nilfs/vesemir.png"));
        nilfs.addCard(new Card(10, "longRange", "Морвран Воорхис", "src/nilfs/voorxis.png"));
        nilfs.addCard(new Card(2, "melee", "Вреемде", "src/nilfs/vreemde.png"));
        nilfs.addCard(new Card(4, "mid", "Зинтия", "src/nilfs/zintia.png"));
        nilfs.addCard(new Card(15, "melee", "Цирилла", "src/nilfs/ziri.png"));
        nilfs.addCard(new Card(5, "melee", "Золтан Хивай", "src/nilfs/zoltan.png"));

        Deck sever = new Deck("Королевства Севера");

        sever.addCard(new Card(6, "longRange", "Баллиста", "src/sever/ballista.png"));
        sever.addCard(new Card(5, "melee", "Бьянка", "src/sever/bianka.png"));
        sever.addCard(new Card(6, "mid", "Детмольд", "src/sever/detmold.png"));
        sever.addCard(new Card(10, "melee", "Эстерад Тиссен", "src/sever/esterad.png"));
        sever.addCard(new Card(15, "melee", "Геральт из Ривии", "src/sever/geralt.png"));
        sever.addCard(new Card(5, "mid", "Кейра Мец", "src/sever/keira.png"));
        sever.addCard(new Card(10, "melee", "Ян Наталис", "src/sever/natalis.png"));
        sever.addCard(new Card(1, "melee", "Реданский пехотинец", "src/sever/pexota.png"));
        sever.addCard(new Card(10, "mid", "Филиппа Эйльхарт", "src/sever/phillipa.png"));
        sever.addCard(new Card(5, "melee", "Эмиель Регис", "src/sever/regis.png"));
        sever.addCard(new Card(5, "mid", "Рубайлы из Кринфрида", "src/sever/rubail.png"));
        sever.addCard(new Card(4, "mid", "Сабрина Глевиссиг", "src/sever/sabrina.png"));
        sever.addCard(new Card(5, "mid", "Шеала де Тансервилль", "src/sever/sheala.png"));
        sever.addCard(new Card(4, "mid", "Шелдон Скаггс", "src/sever/shelden.png"));
        sever.addCard(new Card(6, "longRange", "Осадная башня", "src/sever/siegeTower.png"));
        sever.addCard(new Card(6, "longRange", "Требушет", "src/sever/trebushet.png"));
        sever.addCard(new Card(7, "melee", "Трисс Меригольд", "src/nilfs/triss.png"));
        sever.addCard(new Card(10, "melee", "Вернон Роше", "src/sever/vernon.png"));
        sever.addCard(new Card(6, "melee", "Весимир", "src/nilfs/vesemir.png"));
        sever.addCard(new Card(2, "melee", "Ярпен Зигрин", "src/sever/yarpen.png"));
        sever.addCard(new Card(5, "melee", "Зигфрид из Денесле", "src/sever/zigfrid.png"));
        sever.addCard(new Card(15, "melee", "Цирилла", "src/nilfs/ziri.png"));
        sever.addCard(new Card(5, "melee", "Золтан Хивай", "src/nilfs/zoltan.png"));
        

        MainMenuView mainMenuView = new MainMenuView();
        
        // Передаем главное меню в контроллер
        new MenuController(mainMenuView, nilfs, sever);

        // Отображаем главное меню
        mainMenuView.setVisible(true);
    }
}
