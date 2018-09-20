/**
 * Created on 2018/9/19.
 * @author DuanJiaNing
 */
interface ReadingRepository {

    List<Book> findByReader(String reader)

    void save(Book book)
}