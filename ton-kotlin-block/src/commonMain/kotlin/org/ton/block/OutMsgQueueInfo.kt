package org.ton.block

import kotlinx.serialization.Serializable
import org.ton.cell.CellBuilder
import org.ton.cell.CellSlice
import org.ton.cell.invoke
import org.ton.hashmap.AugDictionary
import org.ton.hashmap.HashMapE
import org.ton.tlb.TlbConstructor
import org.ton.tlb.constructor.tlbConstructor
import org.ton.tlb.loadTlb
import org.ton.tlb.providers.TlbConstructorProvider
import org.ton.tlb.storeTlb

@Serializable
data class OutMsgQueueInfo(
    val out_queue: AugDictionary<EnqueuedMsg, ULong>,
    val proc_info: HashMapE<ProcessedUpto>,
    val ihr_pending: HashMapE<IhrPendingSince>
) {
    companion object : TlbConstructorProvider<OutMsgQueueInfo> by OutMsgQueueInfoTlbConstructor
}

// _ (HashmapAugE 352 EnqueuedMsg uint64) = OutMsgQueue;
// _ (HashmapE 96 ProcessedUpto) = ProcessedInfo;
// _ (HashmapE 320 IhrPendingSince) = IhrPendingInfo;
private object OutMsgQueueInfoTlbConstructor : TlbConstructor<OutMsgQueueInfo>(
    schema = "_ out_queue:OutMsgQueue proc_info:ProcessedInfo ihr_pending:IhrPendingInfo = OutMsgQueueInfo;"
) {
    val outQueue = AugDictionary.tlbCodec(352, EnqueuedMsg, ULong.tlbConstructor())
    val procInfo = HashMapE.tlbCodec(96, ProcessedUpto)
    val ihrPending = HashMapE.tlbCodec(320, IhrPendingSince)

    override fun storeTlb(
        cellBuilder: CellBuilder,
        value: OutMsgQueueInfo
    ) = cellBuilder {
        storeTlb(outQueue, value.out_queue)
        storeTlb(procInfo, value.proc_info)
        storeTlb(ihrPending, value.ihr_pending)
    }

    override fun loadTlb(
        cellSlice: CellSlice
    ): OutMsgQueueInfo = cellSlice {
        val outQueue = loadTlb(outQueue)
        val procInfo = loadTlb(procInfo)
        val ihrPending = loadTlb(ihrPending)
        OutMsgQueueInfo(outQueue, procInfo, ihrPending)
    }
}
