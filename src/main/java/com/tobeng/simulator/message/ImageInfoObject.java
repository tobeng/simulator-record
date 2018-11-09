package com.tobeng.simulator.message;

import lombok.Data;

/**
 * @package com.tobeng.common.message
 * @date 2018/11/2
 */
@Data
public class ImageInfoObject {

    private String Type = "01" ;
    private String StoragePath = "http =//172.16.239.213 =8090/Pics/vhipict/2018/11/02/10/31000000001320000011/0120181102101045-082-1.jpg" ;
    private String DeviceID = "10010001031401000025" ;
    private String ImageID = "10010001031401000025022018110210104571171" ;
    private Integer EventSort = 1 ;
    private String ShotTime = "20181102101045" ;
    private Integer Height = 0 ;
    private String FileFormat = "Jpeg" ;
    private Integer Width = 0;

}
