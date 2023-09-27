package com.ebonyandirony.chess.injection;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceInjector {
    private static final Injector injector = Guice.createInjector(new ChessModule());

    public static Injector getInstance() {
        return injector;
    }
}
