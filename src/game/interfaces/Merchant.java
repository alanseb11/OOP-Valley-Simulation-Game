package game.interfaces;

import java.util.List;

import game.purchaseeffects.MerchantOffer;


/**
 * Represents a Merchant in the game who can offer items for purchase.
 */
public interface Merchant {
    /**
     * Retrieves the list of offerings available from the Merchant.
     *
     * @return a list of MerchantOffer objects representing the items available for purchase
     */
    List<MerchantOffer> getOfferings();
}
