package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }

        System.out.println("Массив для хранения резюме очищен. Количество очищенных элементов " + size);
        size = 0;
    }

    public void save(Resume r) {
        if (size == 10000) {
            System.out.println("Нет места для хранения резюме в массиве. Достигнуто максимальное количество в 10000.");
        } else {
            storage[size] = r;
            System.out.println("Резюме добавлено в массив для хранения.");
            size++;
        }
    }



    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[size-1];
                storage[size-1] = null;
                size--;
                System.out.println("Резюме удалено из массива для хранения. Индекс удаленного резюме " + i);
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] newStorage = new Resume[size];
        for (int i = 0; i < size; i++) {
            newStorage[i] = storage[i];
        }

        return newStorage;
    }

    public int size() {
        return size;
    }
}
