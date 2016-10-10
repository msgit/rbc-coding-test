package com.msadal;

import com.msadal.domain.*;

public interface Visitor {
    void visit(Banana element);
    void visit(Apple element);
    void visit(Orange element);
    void visit(Lemon element);
    void visit(Peach element);
}
