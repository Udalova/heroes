package com.lilia.demo.service;

import com.lilia.demo.domen.Hero;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс должен сохранаять каждого героя в свой файл.
 */
@Service

public class FileHeroService implements HeroService {
    private String FOLDER = "/Users/sudalov/Documents/projects/lilia/heroFiles";

    /**
     * Для получения списка всех героев программа должна считать все файлы
     * из папки /Users/sudalov/Documents/projects/lilia/heroFiles/1_hero.txt
     */
    @Override
    public Collection<Hero> getHeroes() {
        List<Hero> heroes = new ArrayList();
        File pathDir = null;
        String[] pathsFilesAndDir;

        try {
            pathDir = new File(FOLDER);
            pathsFilesAndDir = pathDir.list();

            for (String path : pathsFilesAndDir) {
                try {
                    FileInputStream fstream = new FileInputStream(FOLDER + "/" + path);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                    String strLine;
                    while ((strLine = br.readLine()) != null) {
                        String[] subStr = strLine.split(",");
                        heroes.add(new Hero(Integer.parseInt(subStr[0]),subStr[1]));
                    }
                } catch (IOException e) {
                    System.out.println("Ошибка");
                }

            }
        } catch (Exception e) {
            // Если произошла ошибка
            e.printStackTrace();
        }

    return heroes;
}

    /**
     * Сохраняет героя в файл с именем {id}_hero.txt
     * непример для героя с id = 3 имя файла будет 3_hero.txt
     * Тут нужно не забыть про счетчик
     *
     * @param hero
     * @return возвращает сохраненного героя с присвоенным id
     */
    @Override
    public Hero addHeroes(Hero hero) {
        int id = getLastId() + 1;
        hero.setId(id);
        saveHero(hero);
        return hero;
    }

    /**
     * Перезаписывает файл с героем
     *
     * @param hero
     * @return
     */
    @Override
    public Hero updateHero(Hero hero) {
        deleteHero(hero.getId());
        saveHero(hero);
        return hero;
    }

    /**
     * Удаляет файл с героем
     *
     * @param id
     */
    @Override
    public void deleteHero(Integer id) {
        String fileName = FOLDER + "/" + id + "_hero.txt";
        File file = new File(fileName);
        file.delete();


    }

    /**
     * Получает героя по Id. Т.е. нужно считать файл с нужным именем
     * затем распарсить его и создать объект Hero и вернуть его
     *
     * @param id
     * @return
     */
    @Override
    public Hero getHero(Integer id) {
        String fileName = FOLDER + "/" + id + "_hero.txt";
        Hero hero = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] subStr = line.split(",");
                String name = subStr[1];
                hero = new Hero(id, name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hero;

    }


    private int getLastId() {
        int result = 0;
        try (Stream<Path> paths = Files.walk(Paths.get(FOLDER))) {
            List<Path> pathList = paths
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
            result = pathList.size();
        } catch (IOException ex) {

        }
        return result;
    }
    private void saveHero(Hero hero){
        try (FileWriter writer = new FileWriter(FOLDER + "/" + hero.getId() + "_hero.txt", false)) {
            writer.write(hero.getId() + "," + hero.getName());
            writer.flush();
        } catch (IOException e) {

        }
    }

    private String fileName(Integer id){
        String fileName = FOLDER + "/" + id + "_hero.txt";
        return fileName;
    }

}


