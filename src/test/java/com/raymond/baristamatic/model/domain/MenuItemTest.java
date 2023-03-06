package com.raymond.baristamatic.model.domain;

import com.raymond.baristamatic.service.BaristaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest //Starting application and hitting database for real. I know it's not really a unit test, but it's too time consuming to build mocks.
public class MenuItemTest {
    @Autowired
    BaristaService baristaService;

    @Test
    public void constructor_correctly_calculates_price() {
        List<MenuItem> list = baristaService.getMenu();

        list.sort(Comparator.comparing(MenuItem::getId));
        assertEquals("$2.75",list.get(0).getPrice());
        assertEquals("$2.75",list.get(1).getPrice());
        assertEquals("$2.55",list.get(2).getPrice());
        assertEquals("$3.30",list.get(3).getPrice());
        assertEquals("$3.35",list.get(4).getPrice());
        assertEquals("$2.90",list.get(5).getPrice());
    }

    @Test
    public void constructor_correctly_calculates_amount_available() {
        List<MenuItem> list = baristaService.getMenu();

        list.sort(Comparator.comparing(MenuItem::getId));
        assertEquals(3,list.get(0).getAmountAvailable());
        assertEquals(3,list.get(1).getAmountAvailable());
        assertEquals(5,list.get(2).getAmountAvailable());
        assertEquals(3,list.get(3).getAmountAvailable());
        assertEquals(10,list.get(4).getAmountAvailable());
        assertEquals(5,list.get(5).getAmountAvailable());
    }
}
