package dev.mathias.cointracker.websocket;

import lombok.Getter;

@Getter
public class CoinsDTO {

    private double bitcoinPrice;
    private double ethereumPrice;
    private double shibaInuPrice;
    private double solanaPrice;

    private CoinsDTO(Builder builder){
        this.bitcoinPrice = builder.bitcoinPrice;
        this.ethereumPrice = builder.ethereumPrice;
        this.shibaInuPrice = builder.shibaInuPrice;
        this.solanaPrice = builder.solanaPrice;
    }

    public static class Builder {

        private double bitcoinPrice;
        private double ethereumPrice;
        private double shibaInuPrice;
        private double solanaPrice;

        public Builder setBitcoinPrice(double bitcoinPrice){
            this.bitcoinPrice = bitcoinPrice;
            return this;
        }
        public Builder setEthereumPrice(double ethereumPrice) {
            this.ethereumPrice = ethereumPrice;
            return this;
        }

        public Builder setShibaInuPrice(double shibaInuPrice) {
            this.shibaInuPrice = shibaInuPrice;
            return this;
        }

        public Builder setSolanaPrice(double solanaPrice) {
            this.solanaPrice = solanaPrice;
            return this;
        }

        public CoinsDTO build() {
            return new CoinsDTO(this);
        }
    }
}
