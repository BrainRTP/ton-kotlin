package org.ton.smartcontract.wallet

import org.ton.api.pub.PublicKeyEd25519
import org.ton.api.tonnode.TonNodeBlockIdExt
import org.ton.block.VmStackInt
import org.ton.lite.api.liteserver.LiteServerAccountId
import org.ton.smartcontract.SmartContract

interface GetPublicKeyWallet : SmartContract {
    suspend fun getPublicKey(): PublicKeyEd25519 = getPublicKey(liteApi.getMasterchainInfo().last)

    suspend fun getPublicKey(blockIdExt: TonNodeBlockIdExt): PublicKeyEd25519 {
        val liteServerAccountId = LiteServerAccountId(address())
        val result = liteApi.runSmcMethod(4, blockIdExt, liteServerAccountId, "get_public_key")
        val rawPublicKey = (result.first() as VmStackInt).value.toByteArray()
        return PublicKeyEd25519(rawPublicKey)
    }
}
