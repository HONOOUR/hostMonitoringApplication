# hostMonitoringApplication
application for monitoring hosts

### API Reference

| Summary | path | method | description | parameters |
| --- | --- | --- | --- | --- |
| 호스트 등록, 관리 페이지, 호스트 리스트 조회 | /hosts | Get |  |  |
| 호스트 등록 폼  | /new-host | Get |  |  |
| 호스트 등록 폼 전송 | /new-host | Post |  |  |
| 호스트 수정 폼 | /host/{name} | Get |  | 호스트 이름 |
| 호스트 수정 폼 전송 | /update/host/{name} | Post |  | 호스트 이름 |
| 호스트 등록 삭제 | /delete/host/{name} | Post |  | 호스트 이름 |
|  |  |  |  |  |
|  |  |  |  |  |

### Not supported items

1-e MariaDB 연동하지 않음, 서버를 재 시작하면 등록한 호스트는 사라짐

DDL SQL 파일로 추가
