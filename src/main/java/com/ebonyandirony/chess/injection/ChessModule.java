package com.ebonyandirony.chess.injection;

import com.ebonyandirony.chess.move.verify.AlgebraicNotationVerifier;
import com.ebonyandirony.chess.move.verify.NotationVerifier;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class ChessModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(NotationVerifier.class).annotatedWith(Names.named("algebraic")).to(AlgebraicNotationVerifier.class);
    }

}