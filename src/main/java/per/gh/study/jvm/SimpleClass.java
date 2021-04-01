package per.gh.study.jvm;

/**
 * JVM²ÎÊý£º -server -XX:+UnlockDiagnosticVMOptions -XX:+TraceClassLoading -XX:+PrintAssembly -XX:+LogCompilation -XX:LogFile=target/compile.log
 */
public class SimpleClass {
    public SimpleClass() {
    }

    public int count = 0;

    public static void main(String[] args) {
        SimpleClass simpleClass = new SimpleClass();
        int sum = 0;
        for (int i = 0; i < 10000; i++) {
            sum = simpleClass.add(sum, 1);
        }
        System.out.println(sum);
    }

    /**
     * # {method} {0x000002b8ec252c20} 'add' '(II)I' in 'per/gh/study/jvm/SimpleClass'
     * # this:     rdx:rdx   = 'per/gh/study/jvm/SimpleClass'
     * # parm0:    r8        = int
     * # parm1:    r9        = int
     * #           [sp+0x40]  (sp of caller)
     * [Entry Point]
     * 0x000002b8d369a7a0: mov 0x8(%rdx),%r10d
     * 0x000002b8d369a7a4: shl $0x3,%r10
     * 0x000002b8d369a7a8: cmp %rax,%r10
     * 0x000002b8d369a7ab: jne 0x000002b8d35c5f60  ;   {runtime_call}
     * 0x000002b8d369a7b1: data32 data32 nopw 0x0(%rax,%rax,1)
     * 0x000002b8d369a7bc: data32 data32 xchg %ax,%ax
     * [Verified Entry Point]
     * 0x000002b8d369a7c0: mov %eax,-0x6000(%rsp)
     * 0x000002b8d369a7c7: push %rbp
     * 0x000002b8d369a7c8: sub $0x30,%rsp
     * 0x000002b8d369a7cc: movabs $0x2b8ec252da0,%rax  ;   {metadata(method data for {method} {0x000002b8ec252c20} 'add' '(II)I' in 'per/gh/study/jvm/SimpleClass')}
     * 0x000002b8d369a7d6: mov 0xdc(%rax),%esi
     * 0x000002b8d369a7dc: add $0x8,%esi
     * 0x000002b8d369a7df: mov %esi,0xdc(%rax)
     * 0x000002b8d369a7e5: movabs $0x2b8ec252c18,%rax  ;   {metadata({method} {0x000002b8ec252c20} 'add' '(II)I' in 'per/gh/study/jvm/SimpleClass')}
     * 0x000002b8d369a7ef: and $0x1ff8,%esi
     * 0x000002b8d369a7f5: cmp $0x0,%esi
     * 0x000002b8d369a7f8: je L0001  ;*iload_1
     *                               ; - per.gh.study.jvm.SimpleClass::add@0 (line 22)
     *              L0000: add %r9d,%r8d
     * 0x000002b8d369a801: mov %r8,%rax
     * 0x000002b8d369a804: add $0x30,%rsp
     * 0x000002b8d369a808: pop %rbp
     * 0x000002b8d369a809: test %eax,-0x194a70f(%rip)  # 0x000002b8d1d50100
     *                                                 ;   {poll_return} *** SAFEPOINT POLL ***
     * 0x000002b8d369a80f: retq
     *              L0001: mov %rax,0x8(%rsp)
     * 0x000002b8d369a815: movq $0xffffffffffffffff,(%rsp)
     * 0x000002b8d369a81d: callq 0x000002b8d36833a0  ; OopMap{rdx=Oop off=130}
     *                                               ;*synchronization entry
     *                                               ; - per.gh.study.jvm.SimpleClass::add@-1 (line 22)
     *                                               ;   {runtime_call}
     * 0x000002b8d369a822: jmp L0000
     * 0x000002b8d369a824: nop
     * 0x000002b8d369a825: nop
     * 0x000002b8d369a826: mov 0x2a8(%r15),%rax
     * 0x000002b8d369a82d: movabs $0x0,%r10
     * 0x000002b8d369a837: mov %r10,0x2a8(%r15)
     * 0x000002b8d369a83e: movabs $0x0,%r10
     * 0x000002b8d369a848: mov %r10,0x2b0(%r15)
     * 0x000002b8d369a84f: add $0x30,%rsp
     * 0x000002b8d369a853: pop %rbp
     * 0x000002b8d369a854: jmpq 0x000002b8d367dca0  ;   {runtime_call}
     * 0x000002b8d369a859: hlt
     * 0x000002b8d369a85a: hlt
     * 0x000002b8d369a85b: hlt
     * 0x000002b8d369a85c: hlt
     * 0x000002b8d369a85d: hlt
     * 0x000002b8d369a85e: hlt
     * 0x000002b8d369a85f: hlt
     * [Exception Handler]
     * [Stub Code]
     * 0x000002b8d369a860: callq 0x000002b8d3680920  ;   {no_reloc}
     * 0x000002b8d369a865: mov %rsp,-0x28(%rsp)
     * 0x000002b8d369a86a: sub $0x80,%rsp
     * 0x000002b8d369a871: mov %rax,0x78(%rsp)
     * 0x000002b8d369a876: mov %rcx,0x70(%rsp)
     * 0x000002b8d369a87b: mov %rdx,0x68(%rsp)
     * 0x000002b8d369a880: mov %rbx,0x60(%rsp)
     * 0x000002b8d369a885: mov %rbp,0x50(%rsp)
     * 0x000002b8d369a88a: mov %rsi,0x48(%rsp)
     * 0x000002b8d369a88f: mov %rdi,0x40(%rsp)
     * 0x000002b8d369a894: mov %r8,0x38(%rsp)
     * 0x000002b8d369a899: mov %r9,0x30(%rsp)
     * 0x000002b8d369a89e: mov %r10,0x28(%rsp)
     * 0x000002b8d369a8a3: mov %r11,0x20(%rsp)
     * 0x000002b8d369a8a8: mov %r12,0x18(%rsp)
     * 0x000002b8d369a8ad: mov %r13,0x10(%rsp)
     * 0x000002b8d369a8b2: mov %r14,0x8(%rsp)
     * 0x000002b8d369a8b7: mov %r15,(%rsp)
     * 0x000002b8d369a8bb: movabs $0x6f43e900,%rcx  ;   {external_word}
     * 0x000002b8d369a8c5: movabs $0x2b8d369a865,%rdx  ;   {internal_word}
     * 0x000002b8d369a8cf: mov %rsp,%r8
     * 0x000002b8d369a8d2: and $0xfffffffffffffff0,%rsp
     * 0x000002b8d369a8d6: movabs $0x6f11d2d0,%r10  ;   {runtime_call}
     * 0x000002b8d369a8e0: callq *%r10
     * 0x000002b8d369a8e3: hlt
     * [Deopt Handler Code]
     * 0x000002b8d369a8e4: movabs $0x2b8d369a8e4,%r10  ;   {section_word}
     * 0x000002b8d369a8ee: push %r10
     * 0x000002b8d369a8f0: jmpq 0x000002b8d35c7600  ;   {runtime_call}
     * 0x000002b8d369a8f5: hlt
     * 0x000002b8d369a8f6: hlt
     * 0x000002b8d369a8f7: hlt
     */
    public int add(int m, int n) {
        return m + n;
    }
}