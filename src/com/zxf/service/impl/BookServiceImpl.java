package com.zxf.service.impl;

import com.zxf.entity.Book;
import com.zxf.repository.BookRepository;
import com.zxf.repository.BorrowRepository;
import com.zxf.repository.Impl.BookRepositoryImpl;
import com.zxf.repository.Impl.BorrowRepositoryImpl;
import com.zxf.service.BookService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookServiceImpl implements BookService {
    //设置每页展示的书数量
    private final int LIMIT = 6;
    private BookRepository bookRepository = new BookRepositoryImpl();
    private BorrowRepository borrowRepository = new BorrowRepositoryImpl();
    @Override
    public List<Book> findAll(int page) {
        int index = (page - 1) * LIMIT;
        return bookRepository.findAll(index, LIMIT);
    }

    @Override
    public int getPages() {
        //获得数据库中所有书籍的数量
        int count = bookRepository.count();
        int page = 0;
        if (count % LIMIT == 0) {
            page = count / LIMIT;
        } else {
            page = count / LIMIT + 1;
        }
        return page;
    }

    /**
     * 向bookadmin表中添加借书的一条数据
     * @param bookid
     * @param readerid
     */
    @Override
    public void addBorrow(Integer bookid, Integer readerid) {
        //借书时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String borrowTime = simpleDateFormat.format(date);
        //使用Calendar类来计算还书日期，借书日期+14天
        Calendar calendar = Calendar.getInstance();
        //把今天在一年中多少天取出来，加14之后，把这个数字算出来在这一年中是几月几号
        calendar.set(calendar.DAY_OF_YEAR, calendar.get(calendar.DAY_OF_YEAR) + 14);
        Date date2 = calendar.getTime();
        String returnTime = simpleDateFormat.format(date2);
        borrowRepository.insert(bookid, readerid, borrowTime, returnTime, null, 0);
    }
}
