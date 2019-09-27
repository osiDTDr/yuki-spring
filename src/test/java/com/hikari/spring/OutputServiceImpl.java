package com.hikari.spring;

public class OutputServiceImpl implements OutputService {

    @Override
    public void output(String text) {
        System.out.println(text);
    }

}
