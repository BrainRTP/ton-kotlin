package org.ton.block

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.ton.cell.CellBuilder
import org.ton.cell.CellSlice
import org.ton.cell.invoke
import org.ton.tlb.TlbConstructor
import org.ton.tlb.loadTlb
import org.ton.tlb.providers.TlbConstructorProvider
import org.ton.tlb.storeTlb

@Serializable
@SerialName("trans_split_prepare")
data class TransSplitPrepare(
    val split_info: SplitMergeInfo,
    val storage_ph: Maybe<TrStoragePhase>,
    val compute_ph: TrComputePhase,
    val action: Maybe<TrActionPhase>,
    val aborted: Boolean,
    val destroyed: Boolean
) : TransactionDescr {
    companion object : TlbConstructorProvider<TransSplitPrepare> by TransSplitPrepareTlbConstructor
}

private object TransSplitPrepareTlbConstructor : TlbConstructor<TransSplitPrepare>(
    schema = "trans_split_prepare\$0100 split_info:SplitMergeInfo\n" +
        "  storage_ph:(Maybe TrStoragePhase)\n" +
        "  compute_ph:TrComputePhase action:(Maybe ^TrActionPhase)\n" +
        "  aborted:Bool destroyed:Bool\n" +
        "  = TransactionDescr;"
) {
    val maybeTrStoragePhase = Maybe.tlbCodec(TrStoragePhase)
    val maybeTrActionPhase = Maybe.tlbCodec(TrActionPhase)

    override fun storeTlb(
        cellBuilder: CellBuilder,
        value: TransSplitPrepare
    ) = cellBuilder {
        storeTlb(SplitMergeInfo, value.split_info)
        storeTlb(maybeTrStoragePhase, value.storage_ph)
        storeTlb(TrComputePhase, value.compute_ph)
        storeTlb(maybeTrActionPhase, value.action)
        storeBit(value.aborted)
        storeBit(value.destroyed)
    }

    override fun loadTlb(
        cellSlice: CellSlice
    ): TransSplitPrepare = cellSlice {
        val splitInfo = loadTlb(SplitMergeInfo)
        val storagePh = loadTlb(maybeTrStoragePhase)
        val computePh = loadTlb(TrComputePhase)
        val action = loadTlb(maybeTrActionPhase)
        val aborted = loadBit()
        val destroyed = loadBit()
        TransSplitPrepare(splitInfo, storagePh, computePh, action, aborted, destroyed)
    }
}
