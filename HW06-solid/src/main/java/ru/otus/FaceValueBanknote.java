package ru.otus;

enum FaceValueBanknote {
    BANKNOTE_50 (50),
    BANKNOTE_100 (100),
    BANKNOTE_200 (200),
    BANKNOTE_500 (500),
    BANKNOTE_1000 (1000),
    BANKNOTE_2000 (2000),
    BANKNOTE_5000 (5000);

    private final Integer faceValueBanknote;

    FaceValueBanknote (Integer faceValueBanknote) {
        this.faceValueBanknote = faceValueBanknote;
    }

    public int getFaceValueBanknote() {
        return this.faceValueBanknote;
    }
}
