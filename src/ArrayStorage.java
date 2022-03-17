/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    Resume[] storage = new Resume[10000];
    private int size;


    void clear() {

        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }

        
        System.out.println("Массив для хранения резюме очищен. Количество очищенных элементов "+size);
        size = 0;
    }

    void save(Resume r) {


        if (size == 10000) {
            System.out.println("Нет места для хранения резюме в массиве. Достигнуто максимальное количество в 10000.");
        } else {
            size++;
            storage[size - 1] = r;
            System.out.println("Резюме добавлено в массив для хранения.");
        }

    }

    Resume get(String uuid) {

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }

        return null;
    }

    void delete(String uuid) {
        int indexForDelete = -1;

        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                indexForDelete = i;
            }
        }
        if (indexForDelete == -1) {
            System.out.println("Резюме для удаления в массиве хранения не найдено");
        } else {
            for (int i = indexForDelete; i < size - 1; i++) {
                storage[i] = storage[i + 1];
            }

            storage[size - 1] = null;
            size--;
            System.out.println("Резюме удалено из массива для хранения. Индекс удаленного резюме "+indexForDelete);

        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] newStorage = new Resume[size];
        for (int i=0;i<size;i++){
            newStorage[i] = storage[i];
        }

        return newStorage;
    }

    int size() {
        return size;
    }
}
