package com.dolphpire.api.initializer;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@KeepForApi
public final class Objects {
    @KeepForApi
    static boolean equal(@Nullable Object var0, @Nullable Object var1) {
        return var0 == var1 || var0 != null && var0.equals(var1);
    }

    @KeepForApi
    public static int hashCode(Object... var0) {
        return Arrays.hashCode(var0);
    }

    private Objects() {
        throw new AssertionError("Uninstantiable");
    }

    @KeepForApi
    public static final class ToStringHelper {
        private final ArrayList zzer;
        private final Object zzes;

        private ToStringHelper(Object var1) {
            this.zzes = Preconditions.checkNotNull(var1);
            this.zzer = new ArrayList();
        }

        @KeepForApi
        public final Objects.ToStringHelper add(String var1, @Nullable Object var2) {
            String var3 = Preconditions.checkNotNull(var1);
            String var4 = String.valueOf(var2);
            ((List) this.zzer).add(var3 + "=" + var4);
            return this;
        }

        @KeepForApi
        public final String toString() {
            StringBuilder var1 = (new StringBuilder(100)).append(this.zzes.getClass().getSimpleName()).append('{');
            int var2 = this.zzer.size();

            for(int var3 = 0; var3 < var2; ++var3) {
                var1.append((String)this.zzer.get(var3));
                if (var3 < var2 - 1) {
                    var1.append(", ");
                }
            }

            return var1.append('}').toString();
        }
    }
}
