package kotlinx.coroutines.channels;

import androidx.exifinterface.media.ExifInterface;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;

/* compiled from: ArrayChannel.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000BB9\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012 \u0010\t\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\b¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0013\u001a\u00020\u00122\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0014¢\u0006\u0004\b\u0013\u0010\u0014J\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0016\u001a\u00020\u0015H\u0014¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001c\u001a\u00020\u00172\u0006\u0010\r\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\u001c\u0010\u001dJ#\u0010 \u001a\u00020\u00172\u0006\u0010\r\u001a\u00028\u00002\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030\u001eH\u0014¢\u0006\u0004\b \u0010!J\u0017\u0010#\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u0012H\u0014¢\u0006\u0004\b#\u0010$J\u0011\u0010%\u001a\u0004\u0018\u00010\u0017H\u0014¢\u0006\u0004\b%\u0010&J\u001d\u0010'\u001a\u0004\u0018\u00010\u00172\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030\u001eH\u0014¢\u0006\u0004\b'\u0010(J\u0019\u0010*\u001a\u0004\u0018\u00010)2\u0006\u0010\f\u001a\u00020\u0002H\u0002¢\u0006\u0004\b*\u0010+R\u001e\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170,8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010.R\u0014\u00102\u001a\u00020/8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u00103R\u0016\u00104\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b4\u00103R\u0014\u00105\u001a\u00020\u00128DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b5\u00106R\u0014\u00107\u001a\u00020\u00128DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b7\u00106R\u0014\u00108\u001a\u00020\u00128DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b8\u00106R\u0014\u00109\u001a\u00020\u00128DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b9\u00106R\u0014\u0010:\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b:\u00106R\u0014\u0010;\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u00106R\u0018\u0010>\u001a\u00060<j\u0002`=8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010@¨\u0006A"}, d2 = {"Lkotlinx/coroutines/channels/ArrayChannel;", ExifInterface.LONGITUDE_EAST, "", "capacity", "Lkotlinx/coroutines/channels/BufferOverflow;", "onBufferOverflow", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "onUndeliveredElement", "<init>", "(ILkotlinx/coroutines/channels/BufferOverflow;Lkotlin/jvm/functions/Function1;)V", "currentSize", "element", "enqueueElement", "(ILjava/lang/Object;)V", "Lkotlinx/coroutines/channels/Receive;", "receive", "", "enqueueReceiveInternal", "(Lkotlinx/coroutines/channels/Receive;)Z", "Lkotlinx/coroutines/channels/Send;", "send", "", "enqueueSend", "(Lkotlinx/coroutines/channels/Send;)Ljava/lang/Object;", "ensureCapacity", "(I)V", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "offerSelectInternal", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "wasClosed", "onCancelIdempotent", "(Z)V", "pollInternal", "()Ljava/lang/Object;", "pollSelectInternal", "(Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "Lkotlinx/coroutines/internal/Symbol;", "updateBufferSize", "(I)Lkotlinx/coroutines/internal/Symbol;", "", "buffer", "[Ljava/lang/Object;", "", "getBufferDebugString", "()Ljava/lang/String;", "bufferDebugString", "I", "head", "isBufferAlwaysEmpty", "()Z", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "isClosedForReceive", "isEmpty", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/channels/BufferOverflow;", "kotlinx-coroutines-core", "Lkotlinx/coroutines/channels/AbstractChannel;"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public class ArrayChannel<E> extends AbstractChannel<E> {
    private Object[] buffer;
    private final int capacity;
    private int head;
    private final ReentrantLock lock;
    private final BufferOverflow onBufferOverflow;
    private volatile /* synthetic */ int size;

    /* compiled from: ArrayChannel.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BufferOverflow.values().length];
            iArr[BufferOverflow.SUSPEND.ordinal()] = 1;
            iArr[BufferOverflow.DROP_LATEST.ordinal()] = 2;
            iArr[BufferOverflow.DROP_OLDEST.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected final boolean isBufferAlwaysEmpty() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected final boolean isBufferAlwaysFull() {
        return false;
    }

    public ArrayChannel(int i, BufferOverflow bufferOverflow, Function1<? super E, Unit> function1) {
        super(function1);
        this.capacity = i;
        this.onBufferOverflow = bufferOverflow;
        if (!(i >= 1)) {
            throw new IllegalArgumentException(("ArrayChannel capacity must be at least 1, but " + i + " was specified").toString());
        }
        this.lock = new ReentrantLock();
        Object[] objArr = new Object[Math.min(i, 8)];
        ArraysKt.fill$default(objArr, AbstractChannelKt.EMPTY, 0, 0, 6, (Object) null);
        this.buffer = objArr;
        this.size = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferEmpty() {
        return this.size == 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferFull() {
        return this.size == this.capacity && this.onBufferOverflow == BufferOverflow.SUSPEND;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel, kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return isEmptyImpl();
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel, kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return super.isClosedForReceive();
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
    
        if (r1 == 0) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x001f, code lost:
    
        r2 = takeFirstReceiveOrPeekClosed();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0023, code lost:
    
        if (r2 != null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0028, code lost:
    
        if ((r2 instanceof kotlinx.coroutines.channels.Closed) == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0030, code lost:
    
        kotlin.jvm.internal.Intrinsics.checkNotNull(r2);
        r3 = r2.tryResumeReceive(r6, null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0038, code lost:
    
        if (r3 == null) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003e, code lost:
    
        if (kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED() == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0042, code lost:
    
        if (r3 != kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0044, code lost:
    
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0047, code lost:
    
        if (r3 == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004f, code lost:
    
        throw new java.lang.AssertionError();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0046, code lost:
    
        r3 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0050, code lost:
    
        r5.size = r1;
        r1 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0054, code lost:
    
        r0.unlock();
        r2.completeResumeReceive(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x005e, code lost:
    
        return r2.getOfferResult();
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x002a, code lost:
    
        r5.size = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x002f, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x005f, code lost:
    
        enqueueElement(r1, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0067, code lost:
    
        return kotlinx.coroutines.channels.AbstractChannelKt.OFFER_SUCCESS;
     */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object offerInternal(E r6) {
        /*
            r5 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r5.lock
            java.util.concurrent.locks.Lock r0 = (java.util.concurrent.locks.Lock) r0
            r0.lock()
            int r1 = r5.size     // Catch: java.lang.Throwable -> L68
            kotlinx.coroutines.channels.Closed r2 = r5.getClosedForSend()     // Catch: java.lang.Throwable -> L68
            if (r2 == 0) goto L13
            r0.unlock()
            return r2
        L13:
            kotlinx.coroutines.internal.Symbol r2 = r5.updateBufferSize(r1)     // Catch: java.lang.Throwable -> L68
            if (r2 == 0) goto L1d
            r0.unlock()
            return r2
        L1d:
            if (r1 != 0) goto L5f
        L1f:
            kotlinx.coroutines.channels.ReceiveOrClosed r2 = r5.takeFirstReceiveOrPeekClosed()     // Catch: java.lang.Throwable -> L68
            if (r2 != 0) goto L26
            goto L5f
        L26:
            boolean r3 = r2 instanceof kotlinx.coroutines.channels.Closed     // Catch: java.lang.Throwable -> L68
            if (r3 == 0) goto L30
            r5.size = r1     // Catch: java.lang.Throwable -> L68
            r0.unlock()
            return r2
        L30:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch: java.lang.Throwable -> L68
            r3 = 0
            kotlinx.coroutines.internal.Symbol r3 = r2.tryResumeReceive(r6, r3)     // Catch: java.lang.Throwable -> L68
            if (r3 == 0) goto L1f
            boolean r4 = kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED()     // Catch: java.lang.Throwable -> L68
            if (r4 == 0) goto L50
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN     // Catch: java.lang.Throwable -> L68
            if (r3 != r4) goto L46
            r3 = 1
            goto L47
        L46:
            r3 = 0
        L47:
            if (r3 == 0) goto L4a
            goto L50
        L4a:
            java.lang.AssertionError r6 = new java.lang.AssertionError     // Catch: java.lang.Throwable -> L68
            r6.<init>()     // Catch: java.lang.Throwable -> L68
            throw r6     // Catch: java.lang.Throwable -> L68
        L50:
            r5.size = r1     // Catch: java.lang.Throwable -> L68
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L68
            r0.unlock()
            r2.completeResumeReceive(r6)
            java.lang.Object r6 = r2.getOfferResult()
            return r6
        L5f:
            r5.enqueueElement(r1, r6)     // Catch: java.lang.Throwable -> L68
            kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_SUCCESS     // Catch: java.lang.Throwable -> L68
            r0.unlock()
            return r6
        L68:
            r6 = move-exception
            r0.unlock()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ArrayChannel.offerInternal(java.lang.Object):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
    
        if (r1 == 0) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x001f, code lost:
    
        r2 = describeTryOffer(r5);
        r3 = r6.performAtomicTrySelect(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002a, code lost:
    
        if (r3 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0046, code lost:
    
        if (r3 == kotlinx.coroutines.channels.AbstractChannelKt.OFFER_FAILED) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004a, code lost:
    
        if (r3 == kotlinx.coroutines.internal.AtomicKt.RETRY_ATOMIC) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0050, code lost:
    
        if (r3 == kotlinx.coroutines.selects.SelectKt.getALREADY_SELECTED()) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0054, code lost:
    
        if ((r3 instanceof kotlinx.coroutines.channels.Closed) == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0071, code lost:
    
        throw new java.lang.IllegalStateException(("performAtomicTrySelect(describeTryOffer) returned " + r3).toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0072, code lost:
    
        r4.size = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0077, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x002c, code lost:
    
        r4.size = r1;
        r6 = r2.getResult();
        r1 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0034, code lost:
    
        r0.unlock();
        kotlin.jvm.internal.Intrinsics.checkNotNull(r6);
        r6 = r6;
        r6.completeResumeReceive(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0043, code lost:
    
        return r6.getOfferResult();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007c, code lost:
    
        if (r6.trySelect() != false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x007e, code lost:
    
        r4.size = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0087, code lost:
    
        return kotlinx.coroutines.selects.SelectKt.getALREADY_SELECTED();
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0088, code lost:
    
        enqueueElement(r1, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0090, code lost:
    
        return kotlinx.coroutines.channels.AbstractChannelKt.OFFER_SUCCESS;
     */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object offerSelectInternal(E r5, kotlinx.coroutines.selects.SelectInstance<?> r6) {
        /*
            r4 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r4.lock
            java.util.concurrent.locks.Lock r0 = (java.util.concurrent.locks.Lock) r0
            r0.lock()
            int r1 = r4.size     // Catch: java.lang.Throwable -> L91
            kotlinx.coroutines.channels.Closed r2 = r4.getClosedForSend()     // Catch: java.lang.Throwable -> L91
            if (r2 == 0) goto L13
            r0.unlock()
            return r2
        L13:
            kotlinx.coroutines.internal.Symbol r2 = r4.updateBufferSize(r1)     // Catch: java.lang.Throwable -> L91
            if (r2 == 0) goto L1d
            r0.unlock()
            return r2
        L1d:
            if (r1 != 0) goto L78
        L1f:
            kotlinx.coroutines.channels.AbstractSendChannel$TryOfferDesc r2 = r4.describeTryOffer(r5)     // Catch: java.lang.Throwable -> L91
            r3 = r2
            kotlinx.coroutines.internal.AtomicDesc r3 = (kotlinx.coroutines.internal.AtomicDesc) r3     // Catch: java.lang.Throwable -> L91
            java.lang.Object r3 = r6.performAtomicTrySelect(r3)     // Catch: java.lang.Throwable -> L91
            if (r3 != 0) goto L44
            r4.size = r1     // Catch: java.lang.Throwable -> L91
            java.lang.Object r6 = r2.getResult()     // Catch: java.lang.Throwable -> L91
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L91
            r0.unlock()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            kotlinx.coroutines.channels.ReceiveOrClosed r6 = (kotlinx.coroutines.channels.ReceiveOrClosed) r6
            r6.completeResumeReceive(r5)
            java.lang.Object r5 = r6.getOfferResult()
            return r5
        L44:
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_FAILED     // Catch: java.lang.Throwable -> L91
            if (r3 == r2) goto L78
            java.lang.Object r2 = kotlinx.coroutines.internal.AtomicKt.RETRY_ATOMIC     // Catch: java.lang.Throwable -> L91
            if (r3 == r2) goto L1f
            java.lang.Object r5 = kotlinx.coroutines.selects.SelectKt.getALREADY_SELECTED()     // Catch: java.lang.Throwable -> L91
            if (r3 == r5) goto L72
            boolean r5 = r3 instanceof kotlinx.coroutines.channels.Closed     // Catch: java.lang.Throwable -> L91
            if (r5 == 0) goto L57
            goto L72
        L57:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L91
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L91
            r6.<init>()     // Catch: java.lang.Throwable -> L91
            java.lang.String r1 = "performAtomicTrySelect(describeTryOffer) returned "
            r6.append(r1)     // Catch: java.lang.Throwable -> L91
            r6.append(r3)     // Catch: java.lang.Throwable -> L91
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L91
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L91
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L91
            throw r5     // Catch: java.lang.Throwable -> L91
        L72:
            r4.size = r1     // Catch: java.lang.Throwable -> L91
            r0.unlock()
            return r3
        L78:
            boolean r6 = r6.trySelect()     // Catch: java.lang.Throwable -> L91
            if (r6 != 0) goto L88
            r4.size = r1     // Catch: java.lang.Throwable -> L91
            java.lang.Object r5 = kotlinx.coroutines.selects.SelectKt.getALREADY_SELECTED()     // Catch: java.lang.Throwable -> L91
            r0.unlock()
            return r5
        L88:
            r4.enqueueElement(r1, r5)     // Catch: java.lang.Throwable -> L91
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_SUCCESS     // Catch: java.lang.Throwable -> L91
            r0.unlock()
            return r5
        L91:
            r5 = move-exception
            r0.unlock()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ArrayChannel.offerSelectInternal(java.lang.Object, kotlinx.coroutines.selects.SelectInstance):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public Object enqueueSend(Send send) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return super.enqueueSend(send);
        } finally {
            reentrantLock.unlock();
        }
    }

    private final Symbol updateBufferSize(int currentSize) {
        if (currentSize < this.capacity) {
            this.size = currentSize + 1;
            return null;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[this.onBufferOverflow.ordinal()];
        if (i == 1) {
            return AbstractChannelKt.OFFER_FAILED;
        }
        if (i == 2) {
            return AbstractChannelKt.OFFER_SUCCESS;
        }
        if (i == 3) {
            return null;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final void enqueueElement(int currentSize, E element) {
        if (currentSize < this.capacity) {
            ensureCapacity(currentSize);
            Object[] objArr = this.buffer;
            objArr[(this.head + currentSize) % objArr.length] = element;
            return;
        }
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(this.onBufferOverflow == BufferOverflow.DROP_OLDEST)) {
                throw new AssertionError();
            }
        }
        Object[] objArr2 = this.buffer;
        int i = this.head;
        objArr2[i % objArr2.length] = null;
        objArr2[(currentSize + i) % objArr2.length] = element;
        this.head = (i + 1) % objArr2.length;
    }

    private final void ensureCapacity(int currentSize) {
        Object[] objArr = this.buffer;
        if (currentSize >= objArr.length) {
            int min = Math.min(objArr.length * 2, this.capacity);
            Object[] objArr2 = new Object[min];
            for (int i = 0; i < currentSize; i++) {
                Object[] objArr3 = this.buffer;
                objArr2[i] = objArr3[(this.head + i) % objArr3.length];
            }
            ArraysKt.fill((Symbol[]) objArr2, AbstractChannelKt.EMPTY, currentSize, min);
            this.buffer = objArr2;
            this.head = 0;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    protected Object pollInternal() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i = this.size;
            if (i == 0) {
                Object closedForSend = getClosedForSend();
                if (closedForSend == null) {
                    closedForSend = AbstractChannelKt.POLL_FAILED;
                }
                return closedForSend;
            }
            Object[] objArr = this.buffer;
            int i2 = this.head;
            Object obj = objArr[i2];
            Send send = null;
            objArr[i2] = null;
            this.size = i - 1;
            Object obj2 = AbstractChannelKt.POLL_FAILED;
            if (i == this.capacity) {
                Send send2 = null;
                while (true) {
                    Send takeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
                    if (takeFirstSendOrPeekClosed == null) {
                        send = send2;
                        break;
                    }
                    Intrinsics.checkNotNull(takeFirstSendOrPeekClosed);
                    Symbol tryResumeSend = takeFirstSendOrPeekClosed.tryResumeSend(null);
                    if (tryResumeSend != null) {
                        if (DebugKt.getASSERTIONS_ENABLED()) {
                            if (!(tryResumeSend == CancellableContinuationImplKt.RESUME_TOKEN)) {
                                throw new AssertionError();
                            }
                        }
                        obj2 = takeFirstSendOrPeekClosed.getElement();
                        send = takeFirstSendOrPeekClosed;
                        r6 = true;
                    } else {
                        takeFirstSendOrPeekClosed.undeliveredElement();
                        send2 = takeFirstSendOrPeekClosed;
                    }
                }
            }
            if (obj2 != AbstractChannelKt.POLL_FAILED && !(obj2 instanceof Closed)) {
                this.size = i;
                Object[] objArr2 = this.buffer;
                objArr2[(this.head + i) % objArr2.length] = obj2;
            }
            this.head = (this.head + 1) % this.buffer.length;
            Unit unit = Unit.INSTANCE;
            if (r6) {
                Intrinsics.checkNotNull(send);
                send.completeResumeSend();
            }
            return obj;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0029, code lost:
    
        if (r1 == r8.capacity) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002b, code lost:
    
        r3 = describeTryPoll();
        r7 = r9.performAtomicTrySelect(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0036, code lost:
    
        if (r7 != null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004a, code lost:
    
        if (r7 == kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004e, code lost:
    
        if (r7 == kotlinx.coroutines.internal.AtomicKt.RETRY_ATOMIC) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0054, code lost:
    
        if (r7 != kotlinx.coroutines.selects.SelectKt.getALREADY_SELECTED()) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0056, code lost:
    
        r8.size = r1;
        r8.buffer[r8.head] = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0061, code lost:
    
        return r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0064, code lost:
    
        if ((r7 instanceof kotlinx.coroutines.channels.Closed) == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0066, code lost:
    
        r2 = r7;
        r5 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0046, code lost:
    
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0087, code lost:
    
        if (r2 == kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x008b, code lost:
    
        if ((r2 instanceof kotlinx.coroutines.channels.Closed) != false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x008d, code lost:
    
        r8.size = r1;
        r9 = r8.buffer;
        r9[(r8.head + r1) % r9.length] = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00af, code lost:
    
        r8.head = (r8.head + 1) % r8.buffer.length;
        r9 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00bd, code lost:
    
        if (r3 == false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00bf, code lost:
    
        kotlin.jvm.internal.Intrinsics.checkNotNull(r5);
        ((kotlinx.coroutines.channels.Send) r5).completeResumeSend();
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00c7, code lost:
    
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x009d, code lost:
    
        if (r9.trySelect() != false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x009f, code lost:
    
        r8.size = r1;
        r8.buffer[r8.head] = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00ae, code lost:
    
        return kotlinx.coroutines.selects.SelectKt.getALREADY_SELECTED();
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0083, code lost:
    
        throw new java.lang.IllegalStateException(("performAtomicTrySelect(describeTryOffer) returned " + r7).toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0038, code lost:
    
        r5 = r3.getResult();
        kotlin.jvm.internal.Intrinsics.checkNotNull(r5);
        r2 = ((kotlinx.coroutines.channels.Send) r5).getElement();
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0084, code lost:
    
        r3 = false;
     */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object pollSelectInternal(kotlinx.coroutines.selects.SelectInstance<?> r9) {
        /*
            r8 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r8.lock
            java.util.concurrent.locks.Lock r0 = (java.util.concurrent.locks.Lock) r0
            r0.lock()
            int r1 = r8.size     // Catch: java.lang.Throwable -> Lc8
            if (r1 != 0) goto L17
            kotlinx.coroutines.channels.Closed r9 = r8.getClosedForSend()     // Catch: java.lang.Throwable -> Lc8
            if (r9 != 0) goto L13
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED     // Catch: java.lang.Throwable -> Lc8
        L13:
            r0.unlock()
            return r9
        L17:
            java.lang.Object[] r2 = r8.buffer     // Catch: java.lang.Throwable -> Lc8
            int r3 = r8.head     // Catch: java.lang.Throwable -> Lc8
            r4 = r2[r3]     // Catch: java.lang.Throwable -> Lc8
            r5 = 0
            r2[r3] = r5     // Catch: java.lang.Throwable -> Lc8
            int r2 = r1 + (-1)
            r8.size = r2     // Catch: java.lang.Throwable -> Lc8
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED     // Catch: java.lang.Throwable -> Lc8
            int r3 = r8.capacity     // Catch: java.lang.Throwable -> Lc8
            r6 = 1
            if (r1 != r3) goto L84
        L2b:
            kotlinx.coroutines.channels.AbstractChannel$TryPollDesc r3 = r8.describeTryPoll()     // Catch: java.lang.Throwable -> Lc8
            r7 = r3
            kotlinx.coroutines.internal.AtomicDesc r7 = (kotlinx.coroutines.internal.AtomicDesc) r7     // Catch: java.lang.Throwable -> Lc8
            java.lang.Object r7 = r9.performAtomicTrySelect(r7)     // Catch: java.lang.Throwable -> Lc8
            if (r7 != 0) goto L48
            java.lang.Object r5 = r3.getResult()     // Catch: java.lang.Throwable -> Lc8
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch: java.lang.Throwable -> Lc8
            r2 = r5
            kotlinx.coroutines.channels.Send r2 = (kotlinx.coroutines.channels.Send) r2     // Catch: java.lang.Throwable -> Lc8
            java.lang.Object r2 = r2.getElement()     // Catch: java.lang.Throwable -> Lc8
        L46:
            r3 = 1
            goto L85
        L48:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED     // Catch: java.lang.Throwable -> Lc8
            if (r7 == r3) goto L84
            java.lang.Object r3 = kotlinx.coroutines.internal.AtomicKt.RETRY_ATOMIC     // Catch: java.lang.Throwable -> Lc8
            if (r7 == r3) goto L2b
            java.lang.Object r2 = kotlinx.coroutines.selects.SelectKt.getALREADY_SELECTED()     // Catch: java.lang.Throwable -> Lc8
            if (r7 != r2) goto L62
            r8.size = r1     // Catch: java.lang.Throwable -> Lc8
            java.lang.Object[] r9 = r8.buffer     // Catch: java.lang.Throwable -> Lc8
            int r1 = r8.head     // Catch: java.lang.Throwable -> Lc8
            r9[r1] = r4     // Catch: java.lang.Throwable -> Lc8
            r0.unlock()
            return r7
        L62:
            boolean r2 = r7 instanceof kotlinx.coroutines.channels.Closed     // Catch: java.lang.Throwable -> Lc8
            if (r2 == 0) goto L69
            r2 = r7
            r5 = r2
            goto L46
        L69:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> Lc8
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc8
            r1.<init>()     // Catch: java.lang.Throwable -> Lc8
            java.lang.String r2 = "performAtomicTrySelect(describeTryOffer) returned "
            r1.append(r2)     // Catch: java.lang.Throwable -> Lc8
            r1.append(r7)     // Catch: java.lang.Throwable -> Lc8
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lc8
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> Lc8
            r9.<init>(r1)     // Catch: java.lang.Throwable -> Lc8
            throw r9     // Catch: java.lang.Throwable -> Lc8
        L84:
            r3 = 0
        L85:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED     // Catch: java.lang.Throwable -> Lc8
            if (r2 == r7) goto L99
            boolean r7 = r2 instanceof kotlinx.coroutines.channels.Closed     // Catch: java.lang.Throwable -> Lc8
            if (r7 != 0) goto L99
            r8.size = r1     // Catch: java.lang.Throwable -> Lc8
            java.lang.Object[] r9 = r8.buffer     // Catch: java.lang.Throwable -> Lc8
            int r7 = r8.head     // Catch: java.lang.Throwable -> Lc8
            int r7 = r7 + r1
            int r1 = r9.length     // Catch: java.lang.Throwable -> Lc8
            int r7 = r7 % r1
            r9[r7] = r2     // Catch: java.lang.Throwable -> Lc8
            goto Laf
        L99:
            boolean r9 = r9.trySelect()     // Catch: java.lang.Throwable -> Lc8
            if (r9 != 0) goto Laf
            r8.size = r1     // Catch: java.lang.Throwable -> Lc8
            java.lang.Object[] r9 = r8.buffer     // Catch: java.lang.Throwable -> Lc8
            int r1 = r8.head     // Catch: java.lang.Throwable -> Lc8
            r9[r1] = r4     // Catch: java.lang.Throwable -> Lc8
            java.lang.Object r9 = kotlinx.coroutines.selects.SelectKt.getALREADY_SELECTED()     // Catch: java.lang.Throwable -> Lc8
            r0.unlock()
            return r9
        Laf:
            int r9 = r8.head     // Catch: java.lang.Throwable -> Lc8
            int r9 = r9 + r6
            java.lang.Object[] r1 = r8.buffer     // Catch: java.lang.Throwable -> Lc8
            int r1 = r1.length     // Catch: java.lang.Throwable -> Lc8
            int r9 = r9 % r1
            r8.head = r9     // Catch: java.lang.Throwable -> Lc8
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> Lc8
            r0.unlock()
            if (r3 == 0) goto Lc7
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            kotlinx.coroutines.channels.Send r5 = (kotlinx.coroutines.channels.Send) r5
            r5.completeResumeSend()
        Lc7:
            return r4
        Lc8:
            r9 = move-exception
            r0.unlock()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ArrayChannel.pollSelectInternal(kotlinx.coroutines.selects.SelectInstance):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    public boolean enqueueReceiveInternal(Receive<? super E> receive) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return super.enqueueReceiveInternal(receive);
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    public void onCancelIdempotent(boolean wasClosed) {
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i = this.size;
            UndeliveredElementException undeliveredElementException = null;
            for (int i2 = 0; i2 < i; i2++) {
                Object obj = this.buffer[this.head];
                if (function1 != null && obj != AbstractChannelKt.EMPTY) {
                    undeliveredElementException = OnUndeliveredElementKt.callUndeliveredElementCatchingException(function1, obj, undeliveredElementException);
                }
                this.buffer[this.head] = AbstractChannelKt.EMPTY;
                this.head = (this.head + 1) % this.buffer.length;
            }
            this.size = 0;
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
            super.onCancelIdempotent(wasClosed);
            if (undeliveredElementException != null) {
                throw undeliveredElementException;
            }
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    protected String getBufferDebugString() {
        return "(buffer:capacity=" + this.capacity + ",size=" + this.size + ')';
    }
}
