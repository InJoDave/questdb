/*******************************************************************************
 *     ___                  _   ____  ____
 *    / _ \ _   _  ___  ___| |_|  _ \| __ )
 *   | | | | | | |/ _ \/ __| __| | | |  _ \
 *   | |_| | |_| |  __/\__ \ |_| |_| | |_) |
 *    \__\_\\__,_|\___||___/\__|____/|____/
 *
 *  Copyright (c) 2014-2019 Appsicle
 *  Copyright (c) 2019-2024 QuestDB
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 ******************************************************************************/

package io.questdb.std;

import io.questdb.log.Log;
import io.questdb.std.str.LPSZ;
import io.questdb.std.str.MutableUtf8Sink;
import io.questdb.std.str.Path;

public interface FilesFacade {
    long MAP_FAILED = -1;

    boolean allocate(long fd, long size);

    boolean allowMixedIO(CharSequence root);

    long append(long fd, long buf, long len);

    boolean close(long fd);

    boolean closeRemove(long fd, LPSZ path);

    int copy(LPSZ from, LPSZ to);

    long copyData(long srcFd, long destFd, long offsetSrc, long length);

    long copyData(long srcFd, long destFd, long offsetSrc, long destOffset, long length);

    int copyRecursive(Path src, Path dst, int dirMode);

    int errno();

    boolean exists(LPSZ path);

    boolean exists(long fd);

    void fadvise(long fd, long offset, long len, int advise);

    long findClose(long findPtr);

    long findFirst(LPSZ path);

    long findName(long findPtr);

    int findNext(long findPtr);

    int findType(long findPtr);

    void fsync(long fd);

    void fsyncAndClose(long fd);

    long getDirSize(Path path);

    long getDiskFreeSpace(LPSZ path);

    long getFileLimit();

    int getFileSystemStatus(LPSZ lpszName);

    long getLastModified(LPSZ path);

    long getMapCountLimit();

    long getMapPageSize();

    long getOpenFileCount();

    long getPageSize();

    int hardLink(LPSZ src, LPSZ hardLink);

    int hardLinkDirRecursive(Path src, Path dst, int dirMode);

    boolean isCrossDeviceCopyError(int errno);

    boolean isDirOrSoftLinkDir(LPSZ path);

    boolean isDirOrSoftLinkDirNoDots(Path path, int rootLen, long pUtf8NameZ, int type);

    boolean isDirOrSoftLinkDirNoDots(Path path, int rootLen, long pUtf8NameZ, int type, MutableUtf8Sink nameSink);

    boolean isRestrictedFileSystem();

    boolean isSoftLink(LPSZ softLink);

    void iterateDir(LPSZ path, FindVisitor func);

    long length(long fd);

    long length(LPSZ name);

    int lock(long fd);

    void madvise(long address, long len, int advise);

    int mkdir(LPSZ path, int mode);

    int mkdirs(Path path, int mode);

    long mmap(long fd, long len, long offset, int flags, int memoryTag);

    long mremap(long fd, long addr, long previousSize, long newSize, long offset, int mode, int memoryTag);

    void msync(long addr, long len, boolean async);

    void munmap(long address, long size, int memoryTag);

    long openAppend(LPSZ name);

    long openCleanRW(LPSZ name, long size);

    long openRO(LPSZ name);

    long openRW(LPSZ name, long opts);

    long read(long fd, long buf, long size, long offset);

    long readIntAsUnsignedLong(long fd, long offset);

    boolean readLink(Path softLink, Path readTo);

    byte readNonNegativeByte(long fd, long offset);

    int readNonNegativeInt(long fd, long offset);

    long readNonNegativeLong(long fd, long offset);

    void remove(LPSZ name);

    boolean removeQuiet(LPSZ name);

    int rename(LPSZ from, LPSZ to);

    boolean rmdir(Path name);  // Implementation-specific laziness.

    boolean rmdir(Path name, boolean haltOnError);

    int softLink(LPSZ src, LPSZ softLink);

    int sync();

    boolean touch(LPSZ path);

    boolean truncate(long fd, long size);

    int typeDirOrSoftLinkDirNoDots(Path path, int rootLen, long pUtf8NameZ, int type, MutableUtf8Sink nameSink);

    int unlink(LPSZ softLink);

    boolean unlinkOrRemove(Path path, Log LOG);

    boolean unlinkOrRemove(Path path, int checkedType, Log LOG);

    void walk(Path src, FindVisitor func);

    long write(long fd, long address, long len, long offset);
}
