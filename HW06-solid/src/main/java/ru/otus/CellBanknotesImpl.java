package ru.otus;

import java.util.Objects;

public class CellBanknotesImpl implements CellBanknotes {

        private final FaceValueBanknote faceValueBanknote;

        private final Integer numberFaceValueBanknote;

        public CellBanknotesImpl (FaceValueBanknote faceValueBanknote) {
            this.faceValueBanknote = faceValueBanknote;
            this.numberFaceValueBanknote = faceValueBanknote.getNumberFaceValueBanknote();
        }

        @Override
        public Integer getNumberFaceValueBanknote() {
            return this.numberFaceValueBanknote;
        }

        @Override
        public FaceValueBanknote getFaceValueBanknote() {
            return this.faceValueBanknote;
        }

        @Override
        public int compareTo(CellBanknotes o1) {
            return this.numberFaceValueBanknote - o1.getNumberFaceValueBanknote();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CellBanknotes that = (CellBanknotes) o;
            return faceValueBanknote == that.getFaceValueBanknote() &&
                    numberFaceValueBanknote.equals(that.getNumberFaceValueBanknote());
        }

        @Override
        public int hashCode() {
            return Objects.hash(faceValueBanknote, numberFaceValueBanknote);
        }
}
