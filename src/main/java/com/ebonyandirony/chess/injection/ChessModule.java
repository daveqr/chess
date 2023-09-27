package com.ebonyandirony.chess.injection;

import com.ebonyandirony.chess.move.parser.AlgebraicNotationParser;
import com.ebonyandirony.chess.move.parser.NotationParser;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class ChessModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(NotationParser.class).annotatedWith(Names.named("algebraic")).to(AlgebraicNotationParser.class);
    }

}