package dev.mathias.cointracker.websocket;

public class CombinedPrice {

    private double bitcoinPrice;
    private double ethereumPrice;

    private double shibaInuPrice;
    private double solanaPrice;

    public double getBitcoinPrice() {
        return bitcoinPrice;
    }

    public void setBitcoinPrice(double bitcoinPrice) {
        this.bitcoinPrice = bitcoinPrice;
    }

    public double getEthereumPrice() {
        return ethereumPrice;
    }

    public void setEthereumPrice(double ethereumPrice) {
        this.ethereumPrice = ethereumPrice;
    }

    public double getShibaInuPrice() {
        return shibaInuPrice;
    }

    public void setShibaInuPrice(double shibaInuPrice) {
        this.shibaInuPrice = shibaInuPrice;
    }

    public double getSolanaPrice() {
        return solanaPrice;
    }

    public void setSolanaPrice(double solanaPrice) {
        this.solanaPrice = solanaPrice;
    }
}
